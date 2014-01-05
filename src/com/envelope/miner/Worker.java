package com.envelope.miner;

public class Worker {

	 public int Level;
	 public double Income;
	private double base = 100;

	    public Worker()
	    {
	        Level = 0;
	        Income = 0;
	    }

	    public double Upgrade()
	    {
	    	double c = 1;
	        Level++;
	        Income += c;
	        
	        return c;
	    }    
	    
	    public double UpgradeCost(int lvl)
	    {
	    	if(lvl == 1) return base ;
	    	else return UpgradeCost(lvl - 1) *2;
	    }

	    public double Work()
	    {
	        return Income;
	    }

	    public int getLeavel()
	    {
	        return Level;
	    }

	    
}
