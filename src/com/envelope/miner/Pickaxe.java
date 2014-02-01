package com.envelope.miner;

/**
 * Created by khoga_000 on 12/21/13.
 */
public class Pickaxe {
    public int Level;
    public double Cooldown;
    private double base = 2;
    private Upgrade upgrade;
    public double CurrencyPerClick; // przerobione z: public Currency CurrencyPerClick;


    public Pickaxe()
    {
        Level = 1;
        Cooldown = 1; // 
        CurrencyPerClick = 1;  //z: CurrencyPerClick.Amount = 1;
        
        upgrade = new Upgrade("PICKAXE");
        
    }
    
    public double Upgrade(int possiility)
    {
        Level++;
        if     (possiility == 1) Upgrade1();
        else if(possiility == 2) Upgrade2();
        	return 1;
    }    
    
    public void Upgrade1()
    {
    	CurrencyPerClick += 2;
    	Cooldown += (Cooldown/20);
    }
    
    public void Upgrade2()
    {
    	Cooldown -= (Cooldown/20);
    }
    
    public double UpgradeCost(int lvl) // koszty upgrade'a -> potrzebna zmiana TODO
    {
    	if(lvl == 1) return base;
    	else return UpgradeCost(lvl - 1) *2;
    }

    public double UsePickaxe()
    {
        return CurrencyPerClick;
    }

    public int getLeavel()
    {
        return Level;
    }

    
}
