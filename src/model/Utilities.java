package model;

public class Utilities {

    public static void printPopulation(Population pop){
        for(Chromosome c : pop.getChromosomes()){
            System.out.print(c.toString());
        }
        System.out.println("average fitness = " + pop.getAverageFitness());
    }
}
