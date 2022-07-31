package com.company.Abstracts;


import com.company.GameUnits.Game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Carnivore extends Animal {
    public HashMap<String, Integer> prey = new HashMap<>();


    @Override
    public void eat() {
        starvingCount = (hunt()) ? 0 : starvingCount + 1; // если охота прошла успешно, то счетчик голода обнуляется, иначе - растет
        if (starvingCount == health)
            die();
    }

    public boolean hunt() { // выбираем из всех животных, находящихся на клетке, тех, кто входит в список съедобных.
        List<Animal> food = Game.island.cells[getX()][getY()].RESIDENTS.keySet().stream(). // берем поток типов животных находящихся на клетке
                filter(className -> prey.keySet().contains(className)). // отсеиваем те типы, которые есть в списке prey
                flatMap(className -> Game.island.cells[getX()][getY()].RESIDENTS.get(className).stream()). // меняем поток типов на поток зверей
                sorted((a1, a2) -> (int) (a2.weight - a1.weight)). //сортируем их по весу
                collect(Collectors.toList());

        Iterator<Animal> it = food.iterator();
        double hunger = satiety;            // задаем параметр голода
        while (hunger > 0 && it.hasNext()) { // охотимся, пока голод не уйдет или пока не кончатся жертвы
            Animal animal = it.next();
            if (tryToCatch(animal)) {
                hunger -= animal.weight;
                animal.die();
            }
        }
        return (hunger <= 0);

    }

    public boolean tryToCatch(Animal animal) { //проверка, удалось ли поймать
        int r = ThreadLocalRandom.current().nextInt(100);

        return (prey.get(animal.className) > r);
    }
}
