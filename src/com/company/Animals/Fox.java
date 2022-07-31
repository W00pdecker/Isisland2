package com.company.Animals;

import com.company.Abstracts.Carnivore;
import com.company.GameUnits.Game;
import com.company.GameUnits.Island;

public class Fox extends Carnivore {


    public Fox(int x, int y) {
        setX(x);
        setY(y);
        health = 3;
        weight = 8.0;
        maxAmount = 30;
        speed = 2;
        satiety = 2;
        className = "Fox";
        prey.put("Rabbit", 70);

        synchronized (Game.island.cells[getX()][getY()].foxes) {
            Game.island.cells[getX()][getY()].foxes.add(this);
        }

    }

    @Override
    public void move() {
        synchronized (Game.island.cells[getX()][getY()].foxes) {
            Game.island.cells[getX()][getY()].foxes.remove(this);
        }
        super.move();
        synchronized (Island.cells[getX()][getY()].foxes) {
            Game.island.cells[getX()][getY()].foxes.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
        synchronized (Game.island.cells[getX()][getY()].foxes) {
            Game.island.cells[getX()][getY()].foxes.remove(this);
        }
    }
}
