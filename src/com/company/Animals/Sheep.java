package com.company.Animals;

import com.company.GameUnits.Game;
import com.company.Abstracts.Herbivore;
import com.company.GameUnits.Island;

public class Sheep extends Herbivore {


    public Sheep(int x, int y) {
        setX(x);
        setY(y);
        health = 1;
        weight = 70.0;
        maxAmount = 140;
        speed = 3;
        satiety = 15;
        className = "Sheep";
        synchronized (Game.island.cells[getX()][getY()].sheeps) {
            Game.island.cells[getX()][getY()].sheeps.add(this);
        }
    }

    @Override
    public void move() {
        synchronized (Game.island.cells[getX()][getY()].sheeps) {
            Game.island.cells[getX()][getY()].sheeps.remove(this);
        }
        super.move();
        synchronized (Island.cells[getX()][getY()].sheeps) {
            Game.island.cells[getX()][getY()].sheeps.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
        synchronized (Game.island.cells[getX()][getY()].sheeps) {
            Game.island.cells[getX()][getY()].sheeps.remove(this);
        }
    }



}
