package com.company.GameUnits;

import com.company.Abstracts.Animal;
import com.company.Util.Statistics;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Game extends  Thread {
    public static Island island;
    private static Statistics stat;
    private int days = 0;
    private List<Animal> animals;



    public Game(Island island) {

        this.island = island;
        this.animals = Arrays.stream(island.cells).flatMap(x -> Arrays.stream(x)).
                flatMap(x -> x.residents.values().stream()).
                flatMap(x -> x.stream()).toList();

    }



    @Override
    public void run() {
        System.out.println("Game start");
        stat = new Statistics();
        List<Cell> cells = Arrays.stream(island.cells).flatMap(x -> Arrays.stream(x)).toList();
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(4);
        List<Animal> workers = animals;
        mainPool.scheduleWithFixedDelay(() -> {
            System.out.println("day start");
            ExecutorService servicePool;
            servicePool = Executors.newFixedThreadPool(20);
            workers.forEach(servicePool::submit);
            servicePool.shutdown();
            cells.forEach(Cell::plantGrow);
            showStatistics();
            if (++days > 10) {
                mainPool.shutdownNow();
            }
        },1000, 1000, TimeUnit.MILLISECONDS);
    }

    public void showStatistics() {
        System.out.println("started statistics");
        System.out.println("Days: " + days);
        System.out.println("    Animals on map: " + stat.countAll());
        System.out.println("    Wolfs: " + stat.countWolves() +
                "   Rabbits: " + stat.countRabbits() +
                "   Deers: " + stat.countDeers() +
                "   Sheeps: " + stat.countSheeps() +
                "   Foxes: " + stat.countFoxes());
        System.out.println(    "Plants: " + stat.countPlants());
    }
}
