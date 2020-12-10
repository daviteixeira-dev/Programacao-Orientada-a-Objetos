package davi.agenda1_umunicocontato;

import java.util.ArrayList;
import java.util.Scanner;

/* @author Davi Teixeira */

class Fone{

    public String id;
    public String numero;

    public Fone(String id, String numero){
        this.id = id;
        this.numero = numero;
    }
    
    public String getId(){
        return id;
    }

    public String getNumero(){
        return numero;
    }
    
    public boolean validar(String number){
        String validos = "0123456789().-";
        for(int i = 0; i < number.length();i++){
            char c = number.charAt(i);
            if(validos.indexOf(c) == -1){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return id + ":" + numero;
    }
}

class Contato{
    private String nome;
    private ArrayList<Fone> fones;
    

    public Contato(String nome){
        this.nome = nome;
        this.fones = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public boolean addFone(String id, String number){
        Fone fone = new Fone(id,number);
        if(fone.validar(number)){
            fones.add(new Fone(id,number));
            return true;
        }else{
            System.out.println("fail: fone invalido");
        }
        return false;
    }
    
    public void rmFone(int indice){
        if(indice < 0 || indice >= fones.size())
            return;
        fones.remove(indice);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("- ");
        int indice = 0;
        
        str.append(this.nome);

        for(Fone fone : fones) {
            str.append(" [");
            str.append(indice);
            str.append(":");
            str.append(fone.getId());
            str.append(":");
            str.append(fone.getNumero());
            str.append("]");
            indice++;
        }
        return str.toString();
    }
}

public class Main{
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        Contato contato = new Contato("");
        
        while(true){
            
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;

            }else if(ui[0].equals("init")){
                contato = new Contato(ui[1]);
                 
            }else if(ui[0].equals("add")){

                contato.addFone(ui[1], ui[2]);
            
            }else if(ui[0].equals("rm")){
                contato.rmFone(Integer.parseInt(ui[1]));
            
            }else if(ui[0].equals("show")){
                 System.out.println(contato);
                
            }else{
                System.out.println("fail: comando inválido");
            }
        }
    }
}