package com.envelope.miner;
/**
 * Created by khoga_000 on 12/21/13.
 */
public class University {
    public double Level;
    public Rock Rock;
    public Pickaxe Pickaxe;

    public University()
    {
        Rock = new Rock();
        Pickaxe = new Pickaxe();
        Level = 1;
    }

    public void Upgrade()
    {
        Level++;
    }
}
