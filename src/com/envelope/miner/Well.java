package com.envelope.miner;

import java.util.Random;

public class Well 
{
	public int Level;
	public double Income;
	private double base = 20;
	private double lineRapture;
	Random R ;
	
	public Well()
    {
        Level = 0;
        Income = 0;
        lineRapture = 50;
    }

    public double Upgrade(int posibility)
    {
        Level++;
        if     (posibility == 1) Upgrade1();
        else if(posibility == 2) Upgrade2(); 
        
        return 1;
    }    
    
    public void Upgrade1()
    {
    	Income += 10;
    }
    
    public void Upgrade2()
    {
    	lineRapture -= lineRapture/10; 
    }
    
    public double UpgradeCost(int lvl)
    {
    	if(lvl == 1) return base ;
    	else return UpgradeCost(lvl - 1) *2;
    }
    
    public double RaptureChance()
    {
    	if(R.nextInt(100) > lineRapture)
    		return 0;
    	else
    		return Income;
    	
    }

	public int getLeavel() {
	
		return Level;
	}
}
