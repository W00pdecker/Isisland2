package com.company.Util;

import com.company.Abstracts.Animal;
import com.company.Animals.*;

public class AnimalFactory {

    public static Animal create(String className, int x, int y) {
        return switch (className) {
            case "Wolf" -> new Wolf(x, y);
            case "Rabbit" -> new Rabbit(x, y);
            case "Deer" -> new Deer(x, y);
            case "Sheep" -> new Sheep(x, y);
            case "Fox" -> new Fox(x, y);
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}
