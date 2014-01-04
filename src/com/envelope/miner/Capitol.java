package com.envelope.miner;

import java.util.ArrayList;
import java.util.List;

public class Capitol{
    public double Level;
    public Income Income;
    public PassiveIncome PassiveIncome;
    public Currency Currency;
    public PremiumCurrency PremiumCurrency;
    public PremiumIncome PremiumIncome;
    public List<IComponent> Components;

    public Capitol()
    {
        Income = new Income();
        PassiveIncome = new PassiveIncome();
        Currency = new Currency();
        PremiumCurrency = new PremiumCurrency();
        PremiumIncome = new PremiumIncome();
        Components = new ArrayList<IComponent>();
        Level = 1;
    }

    public void Upgrade()
    {
        Level++;
    }
}
