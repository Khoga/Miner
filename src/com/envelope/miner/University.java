package com.envelope.miner;
/**
 * Created by khoga_000 on 12/21/13.
 */
public class University {
    public double Level;
    public Rock Rock;
    public Pickaxe Pickaxe;
    public Worker Worker;

    public University()
    {
        Rock = new Rock();
        Pickaxe = new Pickaxe();
        Worker = new Worker();   // przy tworzeniu minera beda tworzyæ sie wszystkie budynki, ale z lvl 0
        Level = 1;
    }

    public void Upgrade()
    {
        Level++;
    }
}
