package com.company.Animals;

import com.company.GameUnits.Game;
import com.company.Abstracts.Herbivore;
import com.company.GameUnits.Island;

public class Rabbit extends Herbivore {


    public Rabbit(int x, int y) {
        setX(x);
        setY(y);
        health = 1;
        weight = 2.0;
        maxAmount = 150;
        speed = 2;
        satiety = 0.45;
        className = "Rabbit";
        synchronized (Game.island.cells[getX()][getY()].rabbits) {
            Game.island.cells[getX()][getY()].rabbits.add(this);
        }
    }

    @Override
    public void move() {
        synchronized (Game.island.cells[getX()][getY()].rabbits) {
            Game.island.cells[getX()][getY()].rabbits.remove(this);
        }
        super.move();
        synchronized (Island.cells[getX()][getY()].rabbits) {
            Game.island.cells[getX()][getY()].rabbits.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
        synchronized (Game.island.cells[getX()][getY()].rabbits) {
            Game.island.cells[getX()][getY()].rabbits.remove(this);
        }
    }



}
