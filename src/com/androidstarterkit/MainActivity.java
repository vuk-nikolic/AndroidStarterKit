package com.androidstarterkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity is where it all begins. The starting point of our application
 * @author vuknikolic
 *
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Get "Start Service" button from XML layout 
        // and create anonymous inner class for "click event handler"
        Button startService = (Button) findViewById(R.id.startServiceButton);
        startService.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// "this" object should start MusicPlayerService
				startService(new Intent(MainActivity.this, MusicPlayerService.class));
			}
		});
        
        // End Service Button is wired in XML. Checkout onStopMusicPlayerService method below.
    }
    
    /**
     * This method is binded to R.id.endServiceButton. It's wired in XML layout. 
     * Less code, pretty cool, eh? 
     * @param v
     */
    public void onStopMusicPlayerService(View v) {
		stopService(new Intent(this, MusicPlayerService.class));
	}
    
    public void onMediaLibraryButton(View v) {
    	Intent intent = new Intent(this, MediaLibraryActivity.class);
    	startActivity(intent);
    }
}