package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List toys = new ArrayList<>();
    private List prizeToys = new ArrayList<>();
    private Random random = new Random();
    private String fileName = "toys.dat";

    public ToyStore(){
        loadToys();
    }
    public void addToy(Toy toy){
        toys.add(toy);
        saveToys();
    }
    public void editToyWeight(int id, double weight){
        for (Toy toy:toys) {
            if(toy.getId() == id){
                toy.setWeight(weight);
                saveToys();
                break;
            }
        }
    }

    public void loadToys(){
        try {
            File file = new File(fileName);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                toys = (List) ois.readObject();
                ois.close();
                fis.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Toy play(){
        double totalWeight = 0;
        for (Toy toy : toys){
            totalWeight += toy.getWeight();
        }
        double randomWeight = random.nextDouble() * totalWeight;
        double weightSum = 0;
        for (Toy toy:toys) {
            weightSum += toy.getWeight();
            if (randomWeight <= weightSum){
                prizeToys.add(toy);
                toy.setQuantity(toy.getQuantity() - 1);
                saveToys();
                return toy;
            }
        }
        return null;
    }
    public List getPrizeToys(){
        return prizeToys;
    }
    private void saveToys(){
        try{
            OutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toys);
            oos.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
