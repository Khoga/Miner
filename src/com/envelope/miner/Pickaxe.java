package com.envelope.miner;

/**
 * Created by khoga_000 on 12/21/13.
 */
public class Pickaxe {
    public int Level;
    public double Cooldown;
    private double base = 10;
    public double CurrencyPerClick; // przerobione z: public Currency CurrencyPerClick;

    public Pickaxe()
    {
        Level = 1;
        Cooldown = 2; // 2 sekundy
        CurrencyPerClick = 1;  //z: CurrencyPerClick.Amount = 1;
    }

    public double Upgrade()
    {
        Level++;
        CurrencyPerClick += 100;
        Cooldown += 0.1;
        return 1;
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
