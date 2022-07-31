package com.company.Abstracts;

import com.company.Util.AnimalFactory;
import com.company.Util.Direction;
import com.company.GameUnits.Game;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static com.company.Util.Direction.*;

public abstract class Animal implements Runnable{

    public int health;
    public double weight;
    public int maxAmount;
    public int speed;
    public String className;
    public int reproduceRate;
    public static AtomicInteger actualAmount;
    private int x;   //координаты животного
    private int y;
    public boolean isMated;
    public boolean isDead; // флаг "мертвый/живой"
    public double satiety;
    public int starvingCount = 0;



    public void run() {
        if (!isDead) {
            //на всякий случай проверка, чтоб мертвые не ходили
            isMated = false;
            move();
            eat();
            if (!isMated)
                reproduce();
        }
    }

    public abstract void eat();

    public void reproduce() {
        synchronized (Game.island.cells[x][y].residents) {
            for (Animal animal : Game.island.cells[x][y].residents.get(className)) {
                if (!animal.isMated && Game.island.cells[x][y].residents.get(className).size() < maxAmount) {
                    animal.isMated = true;
                    isMated = true;
                    AnimalFactory.create(className, x, y);
                }
            }
        }
    }

    public void move(){ //двигаемся по острову, если упираемся в границу, меняем направление
        int movePoints = speed;
        Direction direction = choosePath();
        while (movePoints > 0) {
            if (direction == UP) {
                if (y > 0) {
                    y--;
                    movePoints--;
                }
                else direction = choosePath();
            }
            else if (direction == DOWN) {
                if (y < (Game.island.getHeight() - 1)) {
                    y++;
                    movePoints--;
                }
                else direction = choosePath();
            }
            else if (direction == LEFT) {
                if (x > 0) {
                    x--;
                    movePoints--;
                }
                else direction = choosePath();
            }
            else if (direction == RIGHT) {
                if (x < (Game.island.getWidth() - 1)) {
                    x++;
                    movePoints--;
                }
                else direction = choosePath();
            }
        }
    }

    public Direction choosePath() {                 //метод, который случайным образом выбирает направление
        int direction = ThreadLocalRandom.current().nextInt(3);
        return switch (direction) {
            case 0:
                yield UP;
            case 1:
                yield LEFT;
            case 2:
                yield DOWN;
            case 3:
                yield RIGHT;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        };
    }


    public void die() {
        isDead = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
