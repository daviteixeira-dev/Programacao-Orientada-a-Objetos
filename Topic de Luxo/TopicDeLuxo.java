package davi.topicdeluxo;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.Scanner;

class Passageiro {
    String nome;
    int idade;
	
    public Passageiro(String nome, int idade){
	    this.nome = nome;
	    this.idade = idade;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
	
    @Override
    public  String toString(){
	    return "" + this.nome+":"+ this.idade;		
    }
}
	
class Topic{ 
    
    ArrayList <Passageiro> lugares = new ArrayList <>();
    int qtd_de_lugares;
    int qtd_de_preferenciais;
	
    public Topic(int qtd_de_lugares, int qtd_de_preferenciais){
        this.qtd_de_lugares = qtd_de_lugares;
        this.qtd_de_preferenciais = qtd_de_preferenciais;
        
        for (int i = 0; i < qtd_de_lugares; i++){
            lugares.add(null);		
	}		   
    }
    
    private boolean checar(String nome){
        for(int i = 0; i < qtd_de_lugares; i++){
            if(lugares.get(i) != null && lugares.get(i).getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    public void checar_passageiro(String nome){
        if(checar(nome)){
            System.out.println("fail: pass ja esta na topic");
        }else{
            System.out.println("fail: pass nao esta na topic");
        }
    }
    
    public void adicionar_passageiro(String nome, int idade){
        if(checar(nome)){
            System.out.println("fail: pass ja esta na topic");
        }
        
        if(idade > 65){
            for(int i = 0; i < qtd_de_lugares; i++){
                if(lugares.get(i) == null){
                    lugares.set(i , new Passageiro(nome, idade));
                    return;
                }
            }
        }else{
            
            for(int i = qtd_de_preferenciais; i < qtd_de_lugares;i++){
                if(lugares.get(i) == null){
                    lugares.set(i ,new Passageiro(nome, idade));
                    return;
                }
            }
            
            for(int i = 0; i < qtd_de_lugares;i++){
                if(lugares.get(i) == null){
                    lugares.set(i ,new Passageiro(nome, idade));
                    return;
                }
            }
        }
        System.out.println("fail: topic lotada");
    }
    
    public void remover_passageiro(String nome){
        for(int i = 0; i < qtd_de_lugares; i++){
            if(lugares.get(i) != null && lugares.get(i).getNome().equals(nome)){
                lugares.set(i, null);
                return;
            }
        }
        System.out.println("fail: pass nao esta na topic");
    }
    
    @Override
    public String toString() {
        String tostring = "[ ";
        for(int i = 0; i < qtd_de_lugares; i++){
            if(i < qtd_de_preferenciais){
                if(lugares.get(i) != null){
                    tostring += "@"+ lugares.get(i).toString() +" ";
                }else{
                    tostring += "@ ";
                }
            }else{
                if(lugares.get(i) != null){
                    tostring += "="+ lugares.get(i).toString() +" ";
                }else{
                    tostring += "= ";
                }
            }
        }
        tostring += "]";
        return tostring;
    }
}
	    
	    
public class Main{
    public static void main(String[] args){
        
    	Scanner scanner = new Scanner(System.in);
        Topic topic = new Topic(0,0);

        do{
        	
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            System.out.println("$" + line);
            
            if(ui[0].equals("end")){
                break;
                
            }else if(ui[0].equals("init")){
                topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            
            }else if(ui[0].equals("show")){
                System.out.println(topic);
            
            }else if(ui[0].equals("in")){
                if(ui.length == 2){
                    topic.checar_passageiro(ui[1]);
                }else{
                    topic.adicionar_passageiro(ui[1], Integer.parseInt(ui[2]));
                }
 
            }else if (ui[0].equals("out")){
                topic.remover_passageiro(ui[1]);

            }else{
                System.out.println("fail: comando invalido");
            }
        }while(true);
        
        scanner.close();
    }
}