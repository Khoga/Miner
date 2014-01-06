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
    public int possibility1 = 0;
    public int possibility2 = 0;

    public Pickaxe()
    {
        Level = 1;
        Cooldown = 1; // 
        CurrencyPerClick = 1;  //z: CurrencyPerClick.Amount = 1;
        
        upgrade = new Upgrade("PICKAXE");
        upgrade.currentUpgradeID = 11;
        
    	setUpgradePossibility(11);
        
    }
    
    public void setUpgradePossibility(int CurrentUpgadeID)
    {
    	possibility1 = CurrentUpgadeID + 10;
    	possibility2 = CurrentUpgadeID + 11;
    }

    public double Upgrade(int CurrentUpgadeID)
    {
    	upgrade.setCurrentUpgradeID(CurrentUpgadeID);
    	setUpgradePossibility(CurrentUpgadeID);
    	UpgradeList();
        Level++;
        
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

    public void UpgradeList()
    {
    	switch (upgrade.getCurrentUpgradeID()) {
		case 21:
			CurrencyPerClick = 3;
			Cooldown = 1.5;
			break;
		case 22:
			Cooldown = 0.5;
			break;
		case 31:
			CurrencyPerClick = 5;
			Cooldown = 2;
			break;
		case 32:
			CurrencyPerClick = 3;
			Cooldown = 1;
			break;
		case 33:
			CurrencyPerClick = 2;
			Cooldown = 0.3;
			break;
		case 41:
			CurrencyPerClick = 8;
			Cooldown = 2.5;
			break;
		case 42:
			CurrencyPerClick = 5;
			Cooldown = 1.5;
			break;
		case 43:
			CurrencyPerClick = 2;
			Cooldown = 0.5;
			break;
		case 44:
			CurrencyPerClick = 1;
			Cooldown = 0.25;
			break;
			
		default:
			break;
		}
    }
    
}
