package com.envelope.miner;

import java.util.Random;

public class Worker {

	 public int Level;
	 public double Income;
	 private double base = 20;
	 private int doubleChance = 0;
	 Random R ;

	    public Worker()
	    {
	        Level = 0;
	        Income = 0;
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
	    	Income += 1;
	    }
	    
	    public void Upgrade2()
	    {
	    	doubleChance += 10;
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

		public double WorkerDoubleChance() 
		{
			if(R.nextInt(100) < doubleChance) 
				return Income;
			else return 0;
		}

	    
}
