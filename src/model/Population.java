package model;

public class Population {
    private int populationSize;
    private Chromosome[] population;
    private double fittestScore = 0;

    Population(int populationSize) {
        this.populationSize = populationSize;
        population = new Chromosome[populationSize];
        for (int i = 0; i < population.length; i++) {
            population[i] = new Chromosome(true);
        }
    }

    public Chromosome getFittestChromosome(){
        double maxFit = Double.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < population.length; i++) {
            if (maxFit <= population[i].fitnessScore) {
                maxFit = population[i].fitnessScore;
                maxFitIndex = i;
            }
        }
        fittestScore = population[maxFitIndex].fitnessScore;
        return population[maxFitIndex];
    }

    //Get the second most fittest chromosome
    public Chromosome getSecondFittestChromosome() {
        int  maxFit1 = 0;
        int  maxFit2 = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].fitnessScore > population[maxFit1].fitnessScore) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (population[i].fitnessScore > population[maxFit2].fitnessScore) {
                maxFit2 = i;
            }
        }
        return population[maxFit2];
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        double minFitVal = Double.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < population.length; i++) {
            if (minFitVal >= population[i].fitnessScore) {
                minFitVal = population[i].fitnessScore;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //getFittest Methods
    public void calculateAllFitness(){
        for (int i=0;i<population.length;i++){
            population[i].calcFitness();
        }
        getFittestChromosome();
    }

    public double getFittestScore() {
        return fittestScore;
    }

    public double getAverageFitness(){
        double averageScore = 0;
        for(Chromosome c : population){
            averageScore += c.fitnessScore;
        }
        averageScore /= populationSize;
        return averageScore;
    }

    public Chromosome[] getChromosomes() {
        return population;
    }
}
