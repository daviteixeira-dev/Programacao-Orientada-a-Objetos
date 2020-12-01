package davi.tamagotchi.seupetvirtual;

/* @author Davi Teixeira */

import java.util.Scanner;

class Pet{

    private int energyMax = 1, hungryMax = 1, cleanMax = 1;
    private int energy = 1, hungry = 1, clean = 1;
    private int diamonds = 0;
    private int age = 0;
    private boolean alive = true;
    
    //os metodos set são chamados pelos métodos de ação eat, sleep, ...

    private void setEnergy(final int energy) {
        this.energy = energy;
    }

    private void setHungry(final int hungry) {
        this.hungry = hungry;
    }

    private void setClean(final int clean) {
        this.clean = clean;
    }

    public Pet(int energyMax, int hungryMax, int cleanMax){
        this.energyMax = energyMax;
        this.hungryMax = hungryMax;
        this.cleanMax = cleanMax;

        energy = energyMax;
        hungry = hungryMax;
        clean = cleanMax;
    } //Fim Pet
  
    public int getClean(){
        return this.clean;
    }

    public int getHungry(){
        return this.hungry;
    }

    public int getEnergy(){
        return this.energy;
    }

    @Override
    public String toString(){
        return ("E:" + this.energy + "/" + energyMax + ", " + "S:" + this.hungry + "/" + hungryMax + ", " + "L:"
            + this.clean + "/" + cleanMax + ", " + "D:" + this.diamonds + ", " + "I:" + this.age + "\n");
    }

    public void play(){
        this.energy -= 2;
        this.hungry -= 1;
        this.clean -= 3;
        this.diamonds += 1;
        this.age += 1;
        limites();
    }

    public void shower(){
        if(this.clean >= cleanMax){
            System.out.println("seu pet tá limpinho");
        }else{
            this.energy -= 3;
            this.hungry -= 1;
            this.clean = cleanMax;
            this.age += 2;
            limites();
        }
    }

    public void eat(){
        if(this.energy >= energyMax){
            System.out.println("Seu pet está de buxo cheim");
        }else{
            this.energy -= 1;
            this.hungry += 4;
            this.clean -= 2;
            this.age += 1;
            limites();
        }
    }

    public void sleep(){
        if(this.energy > energyMax - 5){
            System.out.println("fail: nao esta com sono");
        }else{
            age += energyMax - energy;
            energy = energyMax;
            this.hungry -= 1;
        }
    }

    public boolean testAlive(){
        if(energy <= 0 || hungry <= 0 || clean <= 0){
            alive = false;
        }
        if(alive == false){
            System.out.println("fail: pet esta morto");
            return false;
        }
        return true;
    }

    public void limites(){
        if(energy > energyMax){
            energy = energyMax;
        }else if(energy <= 0){
            System.out.println("fail: pet morreu de fraqueza");
            energy = 0;
        }
        if(hungry > hungryMax){
            hungry = hungryMax;
        }else if(hungry <= 0){
            System.out.println("fail: pet morreu de fome");
            hungry = 0;
        }
        if(clean > cleanMax){
            clean = cleanMax;
        }else if(clean <= 0){
            System.out.println("fail: pet morreu de sujeira");
            clean = 0;
        }
    }
}

public class Tamagotchi{
    public static void main(String[] args){
        
        Pet pet = new Pet(0,0,0);
        
        Scanner ler = new Scanner(System.in);
        
        while(true){
            
            String comando = ler.nextLine();
            String[] partes = comando.split(" ");
            System.out.println("$"+ comando);
          
            if(partes[0].equals("init")){
                int energy, hungry, clean;
                energy = Integer.parseInt(partes[1]);
                hungry = Integer.parseInt(partes[2]);
                clean = Integer.parseInt(partes[3]);
                pet = new Pet(energy, hungry, clean);
                
            }else if(partes[0].equals("show")){
                System.out.printf(pet.toString());
                
            }else if(partes[0].equals("play")){
                if(pet.testAlive()){
                    pet.play();
                }
                
            }else if(partes[0].equals("eat")){
                if(pet.testAlive()){
                    pet.eat();
                }
                
            }else if(partes[0].equals("sleep")){
              if(pet.testAlive()){
                    pet.sleep();
              }
              
            }else if(partes[0].equals("clean")){
                if(pet.testAlive()){
                    pet.shower();
            }
            
            }else if(partes[0].equals("end")){
                break;
            }else{
                System.out.println("Comando invalido");
    	    }
        }
    }
}