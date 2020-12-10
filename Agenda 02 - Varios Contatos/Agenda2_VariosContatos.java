package davi.agenda2_varioscontatos;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

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

    public List<Fone> getFones(){
        return this.fones;
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

class Agenda{
    private TreeMap<String, Contato> contatos;
    
    public Agenda(){
        contatos = new TreeMap<>();
    }

    private int findContato(String nome){
        Contato cont = getContato(nome);
        if(cont == null)
            return 0;
        return 1;
    }
    
    Contato getContato(String name){
        for(Contato cont: contatos.values()){
            if(cont.getNome().equals(name)){
                return cont;
            }
        }
        return null;
    }
    
    public boolean initContato(String nome){
        Contato cont = getContato(nome);
        if(cont != null) return false;
        contatos.put(nome, new Contato(nome));
        return true;
    }

    public void addContato(String name, String id, String number){
        Contato cont = getContato(name);
        if(cont == null){
            cont = new Contato(name);
            cont.addFone(id, number);
        }
        else if(cont != null){
            cont.addFone(id, number);    
        }
        
    }
    
    public boolean rmContato(String name){
        Contato cont = getContato(name);
        if(cont == null){
            return false;
        }
        contatos.remove(name);
        return true;
    }

    public void search(String label){
        
        ArrayList<Contato> pesquisa = new ArrayList<>();
        
        for(Contato cont : contatos.values()){
            if(cont.toString().indexOf(label) != -1)
                pesquisa.add(cont);
        }
        
        for(int i = 0; i < pesquisa.size(); i++){
            System.out.println(pesquisa.get(i));
        }
    }

    public void rmFoneindice(String name, int indice){
        Contato contatos = getContato(name);
        if(contatos != null)
            contatos.rmFone(indice);
    }

    public ArrayList<Contato> getContatos(){
        return (ArrayList<Contato>) contatos.values();
    }
    
    public void show(){
        for(Contato cont : contatos.values()){
            System.out.println(cont);
        }
    }
}

public class Main{
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        Agenda agenda = new Agenda();
        
        while(true){
            
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
                 
            }else if(ui[0].equals("add")){

                agenda.initContato(ui[1]);
                
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(":");
                    agenda.addContato(ui[1], partes[0], partes[1]);
                }
            
            }else if(ui[0].equals("rmFone")){
                agenda.rmFoneindice(ui[1], Integer.parseInt(ui[2]));
            
            }else if(ui[0].equals("agenda")){
                 //agenda.show();
                 System.out.println(agenda);

            }else if(ui[0].equals("rmContato")){
                agenda.rmContato(ui[1]);
                
            }else if(ui[0].equals("search")){ 
                agenda.search(ui[1]);
                
            }else{
                System.out.println("fail: comando inválido");
            }
        }
    }
}