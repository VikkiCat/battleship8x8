package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Battleship8x8 {

    private final long ships;  //field of long type to store ships locations
    private long shots = 0L;   //field of long type to register shots

    public Battleship8x8(final long ships) {

        this.ships = ships;
    }

    // method for shot's index
    public int findIndex(String shot){
        int j = shot.charAt(0) - 'A' + 1; // column number
        int i = shot.charAt(1) - 48;      // line number
        return (64 - ((i - 1) * 8 + j));
    }

    public boolean shoot(String shot) {
        int index = findIndex(shot);
        BigInteger map = BigInteger.valueOf(ships);
        BigInteger aim = BigInteger.valueOf(shots);
        aim = aim.setBit(index);
        if (map.testBit(index)){
            shots = aim.longValue();
            return true;
        }
        shots = aim.longValue();
        return false;
    }

    public String state() {
        /*
        Returns a string representing state of the map.
        Map string is 8 lines per 8 characters separated by "\n". Use following symbols:
        - '.' - an empty cell
        - '×' - an empty cell that has been shot
        - '☐' - a cell seized by a ship
        - '☒'- a cell seized by a ship that has been shot
        */
        StringBuilder stringBuilder = new StringBuilder();
        BigInteger map = BigInteger.valueOf(ships);
        BigInteger aim = BigInteger.valueOf(shots);
        boolean m, a;
        for (int i = 0; i < 64; i++) {
            m = map.testBit(63-i);
            a = aim.testBit(63-i);
            if (m && a){
                stringBuilder.append('☒');
            } else if (m) {
                stringBuilder.append('☐');
            } else if (a) {
                stringBuilder.append('×');
            } else {
                stringBuilder.append('.');
            }

            if ((i % 9) == 0){
                stringBuilder.insert(i, "\n");
            }

        }
        return stringBuilder.toString();
    }
}
