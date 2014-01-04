package com.envelope.miner.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.envelope.miner.Miner;
import com.google.gson.Gson;

public class ParentActivity extends Activity
{
	
	protected Miner miner;
	private SharedPreferences prefs;
	private Gson gson;
	private Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		editor = prefs.edit();
		
		LoadAllData();
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {

				AutoSave();
				
			}
		}, 1, 1000);
		
	}
	
	public void LoadAllData()
    {
		gson = new Gson();
        String json = prefs.getString("Miner", "");
        if(json.equals("")) ;
        else
        	miner = gson.fromJson(json, Miner.class);
    }
	
	public void AutoSave()
	{
		gson = new Gson();
	    String json = gson.toJson(miner);
	    editor.putString("Miner", json);
	    editor.commit();
	}
}
