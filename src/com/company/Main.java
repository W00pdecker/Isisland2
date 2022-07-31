package com.company;

import com.company.GameUnits.Game;
import com.company.GameUnits.Island;

public class Main {

    public static void main(String[] args) {
        Island island = new Island(20, 20);
        System.out.println(island.cells.length);
        Game game = new Game(island);
        game.start();

    }
}