package com.company.Util;

import com.company.GameUnits.Cell;
import com.company.GameUnits.Game;

import java.util.Arrays;
import java.util.List;

public class Statistics {

    private List<Cell> cells = Arrays.stream(Game.island.cells).flatMap(x -> Arrays.stream(x)).toList();

    public Statistics() {
    }

    public long countAll() {
        return countWolves() + countRabbits() + countDeers() + countSheeps();
    }


    public long countWolves() {
        long amount = cells.stream().flatMap(cell -> cell.wolves.stream()).count();
        return amount;
    }

    public long countFoxes() {
        long amount = cells.stream().flatMap(cell -> cell.foxes.stream()).count();
        return amount;
    }


    public long countRabbits() {
        long amount = cells.stream().flatMap(cell -> cell.rabbits.stream()).count();
        return amount;

    }

    public long countDeers() {
        long amount = cells.stream().flatMap(cell -> cell.deers.stream()).count();
        return amount;

    }

    public long countSheeps() {
        long amount = cells.stream().flatMap(cell -> cell.sheeps.stream()).count();
        return amount;
    }

        public Integer countPlants () {
            Integer plantsAmount = cells.stream().map(cell -> cell.getPlantAmount()).reduce(0, Integer::sum);
            return plantsAmount;
        }
    }

