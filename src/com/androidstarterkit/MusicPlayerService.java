package com.androidstarterkit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * MusicPlayService plays the file from "raw" directory.
 * When it is stopped it sends a Constants.SONG_FINISHED broadcast message.
 * 
 * @author vuknikolic
 *
 */
public class MusicPlayerService extends Service {
	private MediaPlayer player;
	
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(MusicPlayerService.this, R.raw.pandroid);
        player.start();
        player.setLooping(true);
        
        createNotification("You've started the music!", "Music started", "Paranoid Android :)");
        
        // Here is how you send a broadcast, so all receivers that are listening for
        // a message with action Constants.SONG_FINISHED will get it
		sendBroadcast(new Intent().setAction(Constants.SONG_START));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        createNotification("Music playback ended :(", "Music stopped", "Booo!");
        
        // Here is how you send a broadcast, so all receivers that are listening for
        // a message with action Constants.SONG_FINISHED will get it
		sendBroadcast(new Intent().setAction(Constants.SONG_FINISHED));
    }
    
    /**
     * Method that creates a message in the notification bar.
     * @param notificationText text that will be seen when notification bar is pulled all the way up
     * @param title text that will be seen as the notification title
     * @param text description of notification
     */
    public void createNotification(String notificationText, String title, String text) {
		NotificationManager notificationManager = (NotificationManager) 
					getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				notificationText, System.currentTimeMillis());
		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, title, text, activity);
		notification.number += 1;
		notificationManager.notify(0, notification);

	}
        
}
