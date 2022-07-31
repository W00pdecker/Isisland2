package com.company.Animals;

import com.company.Abstracts.Carnivore;
import com.company.GameUnits.Game;
import com.company.GameUnits.Island;

public class Wolf extends Carnivore {


    public Wolf(int x, int y) {
        setX(x);
        setY(y);
        health = 5;
        weight = 50.0;
        maxAmount = 30;
        speed = 3;
        satiety = 8;
        className = "Wolf";
        prey.put("Rabbit", 60);
        prey.put("Deer", 15);
        prey.put("Sheep", 70);

        synchronized (Game.island.cells[getX()][getY()].wolves) {
            Game.island.cells[getX()][getY()].wolves.add(this);
        }

    }

    @Override
    public void move() {
        synchronized (Game.island.cells[getX()][getY()].wolves) {
            Game.island.cells[getX()][getY()].wolves.remove(this);
        }
        super.move();
        synchronized (Island.cells[getX()][getY()].wolves) {
            Game.island.cells[getX()][getY()].wolves.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
        synchronized (Game.island.cells[getX()][getY()].wolves) {
            Game.island.cells[getX()][getY()].wolves.remove(this);
        }
    }
}
