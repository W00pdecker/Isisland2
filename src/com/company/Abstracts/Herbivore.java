package com.company.Abstracts;

import com.company.GameUnits.Game;

public class Herbivore extends Animal {

    int plants = Game.island.cells[getX()][getY()].getPlantAmount();

    @Override
    public void eat() {
        starvingCount = (graze()) ? 0 : starvingCount + 1; // если охота прошла успешно, то счетчик голода обнуляется, иначе - растет
        if (starvingCount == health)
            die();
    }

    public boolean graze() {
        double hunger = satiety;
        if (plants >= hunger) {
            Game.island.cells[getX()][getY()].setPlantAmount(plants - (int) hunger);
            return true;
        }
        else {
            Game.island.cells[getX()][getY()].setPlantAmount(0);
            return false;
        }
    }
}