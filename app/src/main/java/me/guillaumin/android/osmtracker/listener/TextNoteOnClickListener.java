package me.guillaumin.android.osmtracker.listener;

import me.guillaumin.android.osmtracker.activity.TrackLogger;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

/**
 * Manages text note button.
 * 
 * @author Nicolas Guillaumin
 *
 */
public class TextNoteOnClickListener implements OnClickListener {

	
	private TrackLogger tl;
	
	public TextNoteOnClickListener(TrackLogger trackLogger) {
		tl = trackLogger;
	}
	
	@Override
	public void onClick(final View v) {
		// let the TrackLogger activity open and control the dialog
		InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

		tl.showDialog(TrackLogger.DIALOG_TEXT_NOTE);
	}

}
