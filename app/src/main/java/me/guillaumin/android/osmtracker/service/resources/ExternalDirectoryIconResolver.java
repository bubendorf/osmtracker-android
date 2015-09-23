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


	public ExternalDirectoryIconResolver(Resources resources, File baseDir) {
		if (!baseDir.isDirectory()) {
			throw new IllegalArgumentException("baseDir must be a directory. " + baseDir + " is not.");
		}

		this.resources = resources;
		directory = baseDir;
	}
	
	@Override
	public Drawable getIcon(String key) {
		if (key == null) {
			return null;
		} else {
			File iconFile = new File(directory, key);
			if (iconFile.exists() && iconFile.canRead()) {
				Bitmap iconBitmap = BitmapFactory.decodeFile(iconFile.getAbsolutePath());
				BitmapDrawable iconDrawable = new BitmapDrawable(resources, iconBitmap);
				return iconDrawable;
			} else {
				return null;
			}
		}
	}

}
