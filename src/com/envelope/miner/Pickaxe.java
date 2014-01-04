package com.envelope.miner;

/**
 * Created by khoga_000 on 12/21/13.
 */
public class Pickaxe {
    public int Level;
    public double Cooldown;
    public double CurrencyPerClick; // przerobione z: public Currency CurrencyPerClick;

    public Pickaxe()
    {
        Level = 1;
        Cooldown = 2; // 2 sekundy
        CurrencyPerClick = 1;  //z: CurrencyPerClick.Amount = 1;
    }

    public void Upgrade()
    {
        Level++;
        //Cooldown
        //CurrencyPerClick
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
