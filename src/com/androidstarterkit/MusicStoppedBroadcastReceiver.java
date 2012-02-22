package com.androidstarterkit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * MusicStoppedBroadcastReceiver is listening for Constants.SONG_FINISHED intent.
 * When it gets it, the phones starts vibrating.
 * 
 * @author vuknikolic
 *
 */
public class MusicStoppedBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Constants.SONG_FINISHED.equals(intent.getAction())) {
			Toast.makeText(context, "Song ended. Whoa, and we are in the broadcast receiver",
				Toast.LENGTH_LONG).show();
			
			// Vibrate the mobile phone
			Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(2000);
		} else if (Constants.SONG_START.equals(intent.getAction())){
			Toast.makeText(context, "Song started. Rock on!", Toast.LENGTH_LONG).show();
		}
	}
	
	
}
