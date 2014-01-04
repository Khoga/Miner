package com.envelope.miner.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.envelope.miner.Capitol;
import com.envelope.miner.Currency;
import com.envelope.miner.Pickaxe;
import com.envelope.miner.R;



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
         
         FillRightLayout();
         setGoldValues();
         
    }
    
    public void LoadValues()  //pobieranie wszystkich potrzebnych wartosci z Minera
    {
    	currentGold = miner.Capitol.Currency.Amount;
    	incomeGold = miner.Capitol.Income.Amount ;
    	currentPremium = miner.Capitol.PremiumCurrency.Amount;
    	incomePremium = miner.Capitol.PremiumIncome.Amount;
    }
    
    public void setGoldValues()  // wypisywanie na gorej belce aktualnego stanu dubr doczesnych
    {
    	LoadValues();
    	currentGoldTV.setText(currentGold + "" );
    	incomeGoldTV.setText(incomeGold + "");
    	currentPremiumTV.setText(currentPremium + "");
    	incomePremiumTV.setText(incomePremium + "");
    }

    public void RockIVClick(View v) // Click na skale
    {
        miner.Capitol.Currency.Amount += miner.University.Pickaxe.UsePickaxe();
        setGoldValues();
    }
    
    public void FillRightLayout() // wypelnianie prawej czesci
    {
    	TextView tv = (TextView) findViewById(R.id.NameTV);
        tv.setText(miner.Name);
    }
}
