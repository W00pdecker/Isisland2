package com.company.Animals;

import com.company.GameUnits.Game;
import com.company.Abstracts.Herbivore;
import com.company.GameUnits.Island;

public class Deer extends Herbivore {


    public Deer(int x, int y) {
        setX(x);
        setY(y);
        health = 1;
        weight = 300.0;
        maxAmount = 20;
        speed = 4;
        satiety = 50;
        className = "Deer";
        synchronized (Game.island.cells[getX()][getY()].deers) {
            Game.island.cells[getX()][getY()].deers.add(this);
        }
    }

    @Override
    public void move() {
        synchronized (Game.island.cells[getX()][getY()].deers) {
            Game.island.cells[getX()][getY()].deers.remove(this);
        }
        super.move();
        synchronized (Island.cells[getX()][getY()].deers) {
            Game.island.cells[getX()][getY()].deers.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
        synchronized (Game.island.cells[getX()][getY()].deers) {
            Game.island.cells[getX()][getY()].deers.remove(this);
        }
    }



}
