package com.envelope.miner.activities;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.envelope.miner.Miner;
import com.envelope.miner.R;
import com.envelope.miner.Worker;
import com.envelope.miner.rest.Obliczenia;



public class RockActivity extends ParentActivity {

    private ImageView RockIV;
    private TextView currentGoldTV;
    private TextView incomeGoldTV;
    private TextView currentPremiumTV;
    private TextView incomePremiumTV;
	private double currentGold;
	private double incomeGold;
	private double currentPremium;
	private double incomePremium;
	private Obliczenia obliczenia;
	private Timer pickaxeCooldownTimer;
	private Timer ValuesUpdaterTimer;
	private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rock_activity);

        init();

    }

    private void init()
    {
         RockIV = (ImageView) findViewById(R.id.RockIV);
         currentGoldTV = (TextView) findViewById(R.id.CurrentGoldTV);
         incomeGoldTV = (TextView) findViewById(R.id.IncomeGoldTV);
         currentPremiumTV = (TextView) findViewById(R.id.CurrentPremiumTV);
         incomePremiumTV = (TextView) findViewById(R.id.IncomePremiumTV);
         
         obliczenia = new Obliczenia();
         
         FillRightLayout();
         setGoldValues();
         ValuesUpdater() ;
         
    }
    
    private void ValuesUpdater()  // automatyczne aktualizowanie wszystkich zmiennych w aktywnosci
    {
    	ValuesUpdaterTimer = new Timer();
    	ValuesUpdaterTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					
					public void run() {
						setGoldValues();
						FillRightLayout();
					}
				});
			}
		}, 1, 100);
    }
    
    public void ResetAllClick(View v)  // restart wszystkiego ( do testowania )
    {
    	String s = miner.Name;
    	miner = new Miner(s);
    	Intent intent = new Intent(RockActivity.this, RockActivity.class);
    	startActivity(intent);
    
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
    
    public void setPickaxeCooldown(final double time)  // licznik cooldown'u kilofa
    {
    	pickaxeCooldownTimer = new Timer();
    	pickaxeCooldownTimer.scheduleAtFixedRate(new TimerTask() {
			
    		double c = 0;
    		ImageView iv = (ImageView) findViewById(R.id.RockIV);
    		TextView tv = (TextView) findViewById(R.id.CoolDownTV);
    		
			@Override
			public void run() {
				
				runOnUiThread(new Runnable() {
					public void run() {
						c += 100;
						if(c > time) 
						{
							iv.setEnabled(true);
							tv.setText("clickable");
							pickaxeCooldownTimer.cancel();
						}
						else
						{
							iv.setEnabled(false);
							tv.setText("cd: " + obliczenia.round(c/1000, 2));
						}
					}
				});
			}
		}, 1, 100);
    }
    
    public void PickaxeUpgradeClick(View v) // upgrade kilofa
    {
		  dialog = new Dialog(RockActivity.this);

		  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  dialog.setContentView(R.layout.upgrade_dialog);
		  dialog.setCancelable(true);
		  
		  Button poss1 = (Button) dialog.findViewById(R.id.poss1);
		  Button poss2 = (Button) dialog.findViewById(R.id.poss2);
		  poss1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double cena = miner.University.Pickaxe.UpgradeCost(miner.University.Pickaxe.getLeavel()+1);
				int possibility1 = miner.University.Pickaxe.possibility1;
		    	if(miner.Capitol.Currency.Amount >= cena)
		    	{
		    		miner.Capitol.Currency.Amount -= cena;
		    		miner.University.Pickaxe.Upgrade(possibility1);
		    	}
		    	else Toast.makeText(getApplicationContext(), "Brak surowców!", Toast.LENGTH_LONG).show();
		    	dialog.cancel();
			}
		});
		  
		  poss2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double cena = miner.University.Pickaxe.UpgradeCost(miner.University.Pickaxe.getLeavel()+1);
				int possibility2 = miner.University.Pickaxe.possibility2;
		    	if(miner.Capitol.Currency.Amount >= cena)
		    	{
		    		miner.Capitol.Currency.Amount -= cena;
		    		miner.University.Pickaxe.Upgrade(possibility2);
		    	}
		    	else Toast.makeText(getApplicationContext(), "Brak surowców!", Toast.LENGTH_LONG).show();
		    	dialog.cancel();
				
			}
		});
		  
		  dialog.show();
		
    	
    	
    	
    	
    }
    
    public void WorkerUpgradeClick(View v) // upgrade workera
    {
    	if(miner.University.Worker == null) miner.University.Worker = new Worker();
    	
    	miner.Capitol.Income.Amount += miner.University.Worker.Upgrade();
    	Button but = (Button) findViewById(R.id.workerUpgradeBTN);
    	but.setText("worker lvl: " + miner.University.Worker.getLeavel());
    }
    
    public void setGoldValues()  // wypisywanie na gorej belce aktualnego stanu dubr doczesnych
    {
    	LoadValues();
    	
    	int possibility1 = miner.University.Pickaxe.possibility1;
		int possibility2 = miner.University.Pickaxe.possibility2;
		TextView info = (TextView) findViewById(R.id.infoTMP);
		info.setText("poss1: " + possibility1 + "\r\n" + "poss2: " + possibility2 + "\r\n" + "GpC: " + 
	    		miner.University.Pickaxe.CurrencyPerClick + "\r\n" + "cd: " + miner.University.Pickaxe.Cooldown);
		
    	currentGoldTV.setText(currentGold + "" );
    	incomeGoldTV.setText(incomeGold + "");
    	currentPremiumTV.setText(currentPremium + "");
    	incomePremiumTV.setText(incomePremium + "");
    }

    public void RockIVClick(View v) // Click na skale
    {
        miner.Capitol.Currency.Amount += miner.University.Pickaxe.UsePickaxe();
        setGoldValues();
        setPickaxeCooldown(miner.University.Pickaxe.Cooldown*1000);
    }
    
    public void FillRightLayout() // wypelnianie prawej czesci
    {
    	TextView tv = (TextView) findViewById(R.id.NameTV);
        tv.setText(miner.Name);
        
        double cena = miner.University.Pickaxe.UpgradeCost(miner.University.Pickaxe.getLeavel()+1);
    	Button but = (Button) findViewById(R.id.pickaxeUpgradeBTN);
    	but.setText("pickaxe lvl: " + miner.University.Pickaxe.getLeavel() + "\r\n"
    	         + "cost: " + cena);
    	
    	cena = miner.University.Worker.UpgradeCost(miner.University.Worker.getLeavel()+1);
    	but = (Button) findViewById(R.id.workerUpgradeBTN);
    	but.setText("Worker lvl: " + miner.University.Worker.getLeavel() + "\r\n"
    	         + "cost: " + cena);
    }
    
    @Override
    public void onBackPressed() {
    	 Intent intent = new Intent(RockActivity.this, MapActivity.class);
         startActivity(intent);
         finish();
    	super.onBackPressed();
    }
    
    @Override
    protected void onDestroy() {
    	ValuesUpdaterTimer.cancel();
    	super.onDestroy();
    }
}
