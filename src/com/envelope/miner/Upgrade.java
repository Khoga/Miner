package com.envelope.miner;


public class Upgrade 
{
	public static String PICKAXE = "PICKAXE"; 
	public static String WORKER = "WORKER";
	
	public String item = "";
	public int currentUpgradeID = 0;
	
	public Upgrade(String item)
	{
		if(item.equals(PICKAXE)) item = PICKAXE;
		else if(item.equals(WORKER)) item = WORKER;
		//TODO
	}
	
	public int getCurrentUpgradeID()
	{
		return currentUpgradeID;
	}
	
	public void setCurrentUpgradeID(int id)
	{
		currentUpgradeID = id;
	}
}
