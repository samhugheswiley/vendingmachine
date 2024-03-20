package com.sg.vendingmachine.dto;

public class Change {
    private int quarters, dimes, nickels, pennies;

    public Change(int changeInPennies) {
        quarters = changeInPennies / 25;
        changeInPennies %= 25;

        dimes = changeInPennies / 10;
        changeInPennies %= 10;

        nickels = changeInPennies / 5;
        changeInPennies %= 5;

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