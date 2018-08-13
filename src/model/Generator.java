package model;

import java.util.Random;

public class Generator {
    Population population;
    Chromosome fittestChromosome;
    Chromosome secondFittestChromosome;
    Chromosome offSpringChromosome;
    int populationSize;

    private static Generator instance;

    private Generator(){}

    public static Generator getInstance(){
        if(instance == null){
            instance = new Generator();
        }
        return instance;
    }

    public void initializePopulation(int populationSize) {
        this.populationSize = populationSize;
        population = new Population(populationSize);
    }

    public void selection(){
        //Select the most fittest individual
        fittestChromosome = population.getFittestChromosome();
        //Select the second most fittest individual
        secondFittestChromosome = population.getSecondFittestChromosome();
    }

    public void crossover(){
        Chromosome offSpring = new Chromosome(false);
        Chromosome father = fittestChromosome;
        Chromosome mother = secondFittestChromosome;
        for(int i=0;i<fittestChromosome.chromosomeLength;i++){
            int fatherGene = father.getGene(i);
            int motherGene = mother.getGene(i);
            if(fatherGene == motherGene){
                if(fatherGene!= -1)
                    offSpring.setGene(i,fatherGene);
            } else if(fatherGene == -1 && !offSpring.classHasBeenSet(motherGene)){
                offSpring.setGene(i,motherGene);
            } else if(motherGene == -1 && !offSpring.classHasBeenSet(fatherGene)){
                offSpring.setGene(i,fatherGene);
            } else{
                if(offSpring.classHasBeenSet(motherGene) && !offSpring.classHasBeenSet(fatherGene)){
                    offSpring.setGene(i,fatherGene);
                } else if(offSpring.classHasBeenSet(fatherGene) && !offSpring.classHasBeenSet(motherGene)){
                    offSpring.setGene(i,motherGene);
                } else if(offSpring.classHasBeenSet(fatherGene) && offSpring.classHasBeenSet(motherGene)){
                    offSpring.setGene(i,-1);
                } else{
                    int motherGeneScore = mother.calculateGeneScore(motherGene,i);
                    int fatherGeneScore = father.calculateGeneScore(fatherGene,i);
                    if(motherGeneScore > fatherGeneScore)
                        offSpring.setGene(i,motherGene);
                    else
                        offSpring.setGene(i,fatherGene);
                }
            }
        }
        for(int i = 0; i< Data.classes.length; i++){
            if(!offSpring.classHasBeenSet(Data.classes[i].getID())){
                Random rn = new Random();
                int slotID = rn.nextInt(mother.chromosomeLength);
                while(offSpring.getGene(slotID)!=-1){
                    slotID = rn.nextInt(mother.chromosomeLength);
                }
                offSpring.setGene(slotID,Data.classes[i].getID());
            }
        }
        offSpringChromosome = offSpring;
    }

    public void addOffSpring(){

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();
        //Replace least fittest individual from most fittest offspring
        population.getChromosomes()[leastFittestIndex] = offSpringChromosome;
    }

    public void mutation(double mutationRate){
        Random rn = new Random();
        for(int i=0;i<populationSize;i++){
            if(rn.nextInt(10) + 1 < mutationRate*10){
                int slotID = rn.nextInt(population.getChromosomes()[0].chromosomeLength);
                while(population.getChromosomes()[i].getGene(slotID)!=-1){
                    slotID = rn.nextInt(population.getChromosomes()[0].chromosomeLength);
                }
                int classIdx = rn.nextInt(Data.classes.length);
                int classID = Data.classes[classIdx].getID();
                int oldSlotId = population.getChromosomes()[i].getSlotIdx(classID);
                population.getChromosomes()[i].setGene(oldSlotId,-1);
                population.getChromosomes()[i].setGene(slotID,classID);
            }
        }
    }

    public Population getPopulation() {
        return population;
    }

    public Chromosome getFittestChromosome() {
        return fittestChromosome;
    }


}
