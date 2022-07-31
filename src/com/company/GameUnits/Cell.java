package com.company.GameUnits;

import com.company.Abstracts.Animal;
import com.company.Util.AnimalFactory;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {
    private int x;
    private int y;
    private int plantAmount;
    public CopyOnWriteArrayList<Animal> wolves = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Animal> rabbits = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Animal> deers = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Animal> sheeps = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Animal> foxes = new CopyOnWriteArrayList<>();
    public HashMap<String, CopyOnWriteArrayList<Animal>> residents = new HashMap<>(){{
        put("Wolf", wolves);
        put("Rabbit", rabbits);
        put("Deer", deers);
        put("Sheep", sheeps);
        put("Foxes", foxes);
    }};


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        plantGrow();

    }

    public void populate() {
        Random r = new Random();
        if (r.nextInt(10) == 1) {
            AnimalFactory.create("Wolf", x, y);
        }
        AnimalFactory.create("Rabbit", x, y);
        if (r.nextInt(10) <= 2) {
            AnimalFactory.create("Deer", x, y);
        }
        if (r.nextInt(10) <= 5) {
            AnimalFactory.create("Sheep", x, y);
        }
        if (r.nextInt(10) == 2) {
            AnimalFactory.create("Fox", x, y);
        }

    }

    public void plantGrow() {
        plantAmount += ThreadLocalRandom.current().nextInt(200);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlantAmount() {
        return plantAmount;
    }

    public void setPlantAmount(int plantAmount) {
        this.plantAmount = plantAmount;
    }
}
