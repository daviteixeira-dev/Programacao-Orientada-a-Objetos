package davi.pulapulanoparquinho;

/* @author Davi Teixeira */

import java.util.LinkedList;
import java.util.Scanner;

class Crianca{
    private int idade;
    private String nome;

    public Crianca(String nome, int idade){
        this.idade = idade;
        this.nome = nome;
    }
    public int getIdade(){
        return idade;
    }
    public String getNome(){
        return nome;
    }
    public String toString(){
        return nome + ":" + idade;
    }
}

class PulaPula{
    private LinkedList<Crianca> waiting;
    private LinkedList<Crianca> playing;
    

    public PulaPula(){
        waiting = new LinkedList<>();
        playing = new LinkedList<>();
    }

    
    public void arrive(Crianca kid){
        waiting.addFirst(kid);
    }

    public void in(){
        playing.addFirst(waiting.getLast());
        waiting.removeLast();
    }

    public void out(){
        waiting.addFirst(playing.getLast());
        playing.removeLast();
    }

    public String toString(){
        String saida = "=> ";
        for(Crianca kid : waiting)
            saida += kid + " ";
        saida += "=> ";
        saida += "[ ";
        for(Crianca kid : playing)
            saida += kid + " ";

        return saida += "]";
    }

}

public class Main{
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        PulaPula trampolim = new PulaPula();
        
        do{
            
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            System.out.println("$" + line);

            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("chegou")){
                trampolim.arrive(new Crianca(ui[1], Integer.parseInt(ui[2])));
            }else if(ui[0].equals("entrar")){
                trampolim.in();
            }else if(ui[0].equals("sair")){
                trampolim.out();
            }else if(ui[0].equals("show")){
                System.out.println(trampolim);
            }else{
                System.out.println("fail: comando invalido");
            }
        }while(true);
        
        scanner.close();
    }
}