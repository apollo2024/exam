package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        Toy prizeToy = store.play();
        if (prizeToy == null){
            System.out.println("все призы розыграны");
        }
        else {
            System.out.println("вы выиграли" + prizeToy.getName());
            List prizeToys = store.getPrizeToys();
            prizeToys.add(prizeToy);
        }
    }
}