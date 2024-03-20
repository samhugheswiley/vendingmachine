package com.sg.vendingmachine.dto;

public class Change {
    private int quarters, dimes, nickels, pennies;

    public Change(int changeInPennies) {
        quarters = changeInPennies / Coin.QUARTER.getValue();
        changeInPennies %= Coin.QUARTER.getValue();

        dimes = changeInPennies / Coin.DIME.getValue();
        changeInPennies %= Coin.DIME.getValue();

        nickels = changeInPennies / Coin.NICKEL.getValue();
        changeInPennies %= Coin.NICKEL.getValue();

        pennies = changeInPennies;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
}