package com.envelope.miner.activities;

import com.envelope.miner.R;
import com.envelope.miner.R.layout;
import com.envelope.miner.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MarketMap extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.market_map, menu);
		return true;
	}

	  @Override
	    public void onBackPressed() {
	    	 Intent intent = new Intent(MarketMap.this, MapActivity.class);
	         startActivity(intent);
	         finish();
	    	super.onBackPressed();
	    }
}
