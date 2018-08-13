package model;

public class Driver {

    private static int populationSize = 10;
    private static int maxGenerations = 10000;
    private static double mutationRate = 0.5;

    public static Chromosome generateTimeTable(){

        Generator gen = Generator.getInstance();
        gen.initializePopulation(populationSize);

        gen.getPopulation().calculateAllFitness();

        Utilities.printPopulation(gen.getPopulation());
        int generationCount = 0;
        System.out.println("Generation: " + generationCount + " averageFitness: "
                + gen.getPopulation().getAverageFitness()+" Fittest: " + gen.getPopulation().getFittestScore()+ "\n");

        while (gen.getPopulation().getFittestScore() < 1 && generationCount < maxGenerations) {
            generationCount++;
            //Do selection
            gen.selection();
            //Do crossover
            gen.crossover();
            //Do mutation under a random probability
            gen.mutation(mutationRate);
            //Add fittest offspring to population
            gen.addOffSpring();
            //Calculate new fitness value
            gen.getPopulation().calculateAllFitness();
            Utilities.printPopulation(gen.getPopulation());
            System.out.println("Generation: " + generationCount + " averageFitness: "
                    + gen.getPopulation().getAverageFitness()+" Fittest: " + gen.getPopulation().getFittestScore());
        }
        if((Math.abs(gen.getPopulation().getFittestScore() - 1.0)) < 1e-9)
            return gen.getPopulation().getFittestChromosome();
        else    return null;
    }
}
