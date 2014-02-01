package com.envelope.miner.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.envelope.miner.Miner;
import com.envelope.miner.rest.Obliczenia;
import com.google.gson.Gson;

public class ParentActivity extends Activity
{
	
	protected Miner miner;
	private SharedPreferences prefs;
	private Gson gson;
	private Editor editor;
	private Obliczenia obliczenia;
	private Timer AutoSaveTimer;
	private Timer AutoConvertIncomeToGoldTimer;
	public double currentGold;
	public double incomeGold;
	public double currentPremium;
	public double incomePremium;
	public static String VALUES = "VALUES";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		editor = prefs.edit();
		
		/*
		 *  Wszystkie Aktywnoœci dziedzicza po ParentActivity, dlatego tez maja dostep do pola MINER - 
		 *  ono zawiera wszystko.
		 * 
		 */
		
		Log.i("cykl Parent", "onCreate");
		
		obliczenia = new Obliczenia();
		
			LoadAllData();
		if(miner != null)
		{	
			AutoSave();
			AutoConvertIncomeToGold();
		}
	}

	
	public void AutoConvertIncomeToGold()
	{
		AutoConvertIncomeToGoldTimer = new Timer();
		AutoConvertIncomeToGoldTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				
				if(miner.University.Worker == null)
					miner.Capitol.Currency.Amount += miner.University.Worker.WorkerDoubleChance()/10;
				if(miner.University.Well == null)
					miner.Capitol.Currency.Amount -= miner.University.Well.RaptureChance()/10;
				
				miner.Capitol.Currency.Amount += miner.Capitol.Income.Amount / 10;
				miner.Capitol.PremiumCurrency.Amount += miner.Capitol.PremiumIncome.Amount / 10;
			}
		}, 1, 100);
	}
	
    public void LoadValues()  //pobieranie wszystkich potrzebnych wartosci z Minera
    {
    	currentGold = miner.Capitol.Currency.Amount;
    	incomeGold = miner.Capitol.Income.Amount ;
    	currentPremium = miner.Capitol.PremiumCurrency.Amount;
    	incomePremium = miner.Capitol.PremiumIncome.Amount;
    	
    	currentGold = obliczenia.round(currentGold, 2);
    	incomeGold = obliczenia.round(incomeGold, 2);
    	currentPremium = obliczenia.round(currentPremium, 2);
    	incomePremium = obliczenia.round(incomePremium, 2);
    }
	
	private void sendBroadcast()  // TODO
	{
//		Intent intent = new Intent(VALUES );
//		intent.putExtra("realSpeed", miner);
//		sendBroadcast(intent);
	}
	
	public void LoadAllData()  // wczytuje wszystkie informacje //TODO bedzie wczytywal tez z serwera jesli isOnline 
    {
		gson = new Gson();
        String json = prefs.getString("Miner", "");
        if(json.equals("")) ;
        else
        	miner = gson.fromJson(json, Miner.class);
    }
	
	public void AutoSave()   /*
					 Automatyczny zapis wszystkiego co sekunde - jesli apka sie wykrzaczy to informacje
					 z minera sie nie zapisza i apka po restarcie nie bedzie szalala
					 */
	{
		
		 AutoSaveTimer = new Timer();
		 AutoSaveTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {

				gson = new Gson();
			    String json = gson.toJson(miner);
			    editor.putString("Miner", json);
			    editor.commit();	
			}
		}, 1, 1000);
		
	}
	
	@Override
	protected void onStop() 
	{
		Log.i("cykl Parent", "onStop");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() { 
/*
 * przy przejsciach pomiedzy aktywnosciami restart wszystkich timerow 
 * (wzgledy bezpieczenstwa - zbyt duzej utraty pamieci)
 */
		gson = new Gson();
	    String json = gson.toJson(miner);
	    editor.putString("Miner", json);
	    editor.commit();
	    Log.i("cykl Parent", "onDestroy");
	    
	    if(AutoConvertIncomeToGoldTimer != null)
	    	AutoConvertIncomeToGoldTimer.cancel();
	    if(AutoSaveTimer != null)
	    	AutoSaveTimer.cancel();
		super.onDestroy();
	}
}
