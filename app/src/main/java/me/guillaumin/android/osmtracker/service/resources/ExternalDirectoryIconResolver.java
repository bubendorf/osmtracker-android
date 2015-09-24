package me.guillaumin.android.osmtracker.service.resources;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;

/**
 * Implementation of {@link IconResolver} which reads icon
 * from an external directory.
 * 
 * @author Nicolas Guillaumin
 *
 */
public class ExternalDirectoryIconResolver implements IconResolver {

	private Resources resources;

	/**
	 * Base directory to read icon files.
	 */
	private File directory;
	private IconResolver otherIconResolver;

	public ExternalDirectoryIconResolver(Resources resources, File baseDir, IconResolver otherIconResolver) {
		if (!baseDir.isDirectory()) {
			throw new IllegalArgumentException("baseDir must be a directory. " + baseDir + " is not.");
		}

		this.resources = resources;
		directory = baseDir;
		this.otherIconResolver = otherIconResolver;
	}
	
	@Override
	public Drawable getIcon(String key) {
		if (key == null) {
			return null;
		} else {
			// First try to get the Icon from the otherIconResolver
			Drawable drawable = otherIconResolver.getIcon(key);
			if (drawable != null) {
				return drawable;
			}

			File iconFile = new File(directory, key);
			if (iconFile.exists() && iconFile.canRead()) {
				Bitmap iconBitmap = BitmapFactory.decodeFile(iconFile.getAbsolutePath());
				BitmapDrawable iconDrawable = new BitmapDrawable(resources, iconBitmap);
				int xDpi = (int)resources.getDisplayMetrics().xdpi;
				if (iconBitmap.getWidth() <= 32) {
					// Make such small icons larger!
					iconDrawable.setTargetDensity(3 * xDpi);
				} else if (iconBitmap.getWidth() <= 64) {
					iconDrawable.setTargetDensity((int)(1.5 * xDpi));
				}
				return iconDrawable;
			} else {
				return null;
			}
		}
	}

}
