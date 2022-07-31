package com.company.GameUnits;

import com.company.Abstracts.Animal;
import com.company.Util.Statistics;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Game extends  Thread {
    public static Island island;
    private static Statistics stat;
    private static int days = 0;
    private List<Animal> animals;



    public Game(Island island) {   // creating island and gathering all threads in List<animal>.

        this.island = island;
        this.animals = Arrays.stream(island.cells).flatMap(x -> Arrays.stream(x)).
                flatMap(x -> x.RESIDENTS.values().stream()).
                flatMap(x -> x.stream()).toList();

    }



    @Override
    public void run() {
        System.out.println("Game start");
        stat = new Statistics();
        List<Cell> cells = Arrays.stream(island.cells).flatMap(x -> Arrays.stream(x)).toList();
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(4);
        mainPool.scheduleWithFixedDelay(() -> {
            animals = Arrays.stream(island.cells).flatMap(x -> Arrays.stream(x)).
                    flatMap(x -> x.RESIDENTS.values().stream()).
                    flatMap(x -> x.stream()).toList(); //collecting new animals to list
            System.out.println("day start");
            ExecutorService servicePool;
            servicePool = Executors.newFixedThreadPool(20);
            animals.forEach(servicePool::submit);
            servicePool.shutdown();
            cells.forEach(Cell::plantGrow); // at the end of a turn growing some new plants
            stat.showStatistics();
            if (++days > 10) {
                mainPool.shutdownNow();
            }
        },1000, 1000, TimeUnit.MILLISECONDS);
    }

    public static int getDays() {
        return days;
    }
}
