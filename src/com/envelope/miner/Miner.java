package com.envelope.miner;

import java.util.jar.Attributes;

/**
 * Created by khoga_000 on 12/21/13.
 */
public class Miner {
    public String Name;
    public String Password;
    public String Email;
    public Capitol Capitol;
    public University University;

    public Miner(String Name) {
        this.Name = Name;
        Password = "";
        Email = "";
        Capitol = new Capitol();
        University = new University();
    }

    public Miner(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
        Email = "";
        Capitol = new Capitol();
        University = new University();
    }

    public Miner(String Name, String Password, String Email) {
        this.Name = Name;
        this.Password = Password;
        this.Email = Email;
        Capitol = new Capitol();
        University = new University();
    }
}
