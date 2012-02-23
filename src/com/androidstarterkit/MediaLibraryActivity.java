package com.androidstarterkit;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MediaLibraryActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getSongsFromLibrary()));
	}
	
	public List<String> getSongsFromLibrary() {
		List<String> songs = new ArrayList<String>();
		ContentResolver contentResolver = getContentResolver();

		Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
		    // query failed, handle error.
			Log.d("TEST", "Whoa error, no cursor?");
		} else if (!cursor.moveToFirst()) {
		    // no media on the device
			Log.d("TEST", "No media?!");
		} else {
		    int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
		    int artistColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
		    int albumColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ALBUM);
		    do {
		       String thisTitle = cursor.getString(titleColumn);
		       String thisArtist = cursor.getString(artistColumn);
		       String thisAlbum = cursor.getString(albumColumn);
		       
		       songs.add(thisArtist + "\n(" + thisAlbum + ")\n" + thisTitle);
		    } while (cursor.moveToNext());
		}
		
		return songs;
	}
}
