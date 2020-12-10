package davi.agenda3_favoritos;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

class Fone{
    
    public String id;
    public String number;
    
    public Fone(String id, String number){
        this.id = id;
        this.number = number;
    }
    
    public String toString(){
        return id + ":" + number;
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
}

class Contato{
    private String name;
    private ArrayList<Fone> fones  = new ArrayList<>();
    public boolean favorited;
    
    public Contato(String name){
        this.name = name;
        this.favorited = false;
    }
    
    public boolean addFone(String id, String number){
        Fone fone = new Fone(id, number);
        if(fone.validar(number)){
            fones.add(new Fone(id, number));
            return true;
        }else{
            System.out.println("fail: fone invalido");
        }
        return false;
    }
    
    public void rmFone(int indice){
        for(int i = 0; i < fones.size(); i++){
            Fone fone = fones.get(i);
            if(i == indice){
                fones.remove(fones.get(i));
            }
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setFavorited(boolean favorito){
        this.favorited = favorito;
    }
    
    public boolean isFavorited(){
        return this.favorited;
    }
    
    @Override
    public String toString(){
        String out =  "";
        if(isFavorited()){
            out += "@ " + this.name;
        }
        else{
            out += "- " + this.name;
        }
        if(fones.size() > 0)
            out += " ";
        for(int i = 0; i < fones.size(); i++){
            Fone fone = fones.get(i);
            out += "[" + i + ":"+ fone + "]";
        }return out;
    }
}

class Agenda03{
    
    TreeMap<String, Contato> contato;
    TreeMap<String, Contato> favoritos;
    
    public Agenda03(){
        contato = new TreeMap<>();
        favoritos = new TreeMap<>();
    }
    
    private int findContato(String nome){
        Contato cont = getContato(nome);
        if(cont == null)
            return 0;
        return 1;
    }
    
    Contato getContato(String name){
        for(Contato cont: contato.values()){
            if(cont.getName().equals(name)){
                return cont;
            }
        }
        return null;
    }

    public boolean initContato(String nome){
        Contato cont = getContato(nome);
        if(cont != null) return false;
        contato.put(nome, new Contato(nome));
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
        if(cont.isFavorited())
            favoritos.remove(name);
        contato.remove(name);
        return true;
    }
    
    public void rmFoneindice(String name, int indice){
        Contato contatos = getContato(name);
        if(contatos != null)
            contatos.rmFone(indice);
    }
    
    public void search(String label){
        
        ArrayList<Contato> pesquisa = new ArrayList<>();
        
        for(Contato cont : contato.values()){
            if(cont.toString().indexOf(label) != -1)
                pesquisa.add(cont);
        }
        
        for(int i = 0; i < pesquisa.size(); i++){
            System.out.println(pesquisa.get(i));
        }   
    }
    
    public void favoritar(String id){
        Contato cont = getContato(id);
        if(cont == null)
            return;
        if(cont.isFavorited())
            return;
        cont.setFavorited(true);
        favoritos.put(id, cont);
    }
    
    
    public void desfavoritar(String id){
        Contato cont = getContato(id);
        if(cont == null)
            return;
        if(!cont.isFavorited())
            return;
        cont.setFavorited(false);
        favoritos.remove(id);
    }
    
    public void favorited(){
        for(Contato cont: favoritos.values()){
            System.out.println(cont);
        }
    }
    
    public ArrayList<Contato> getContatos(){
        return (ArrayList<Contato>) contato.values();
    }
    
    
    public void show(){
        for(Contato cont : contato.values()){
            System.out.println(cont);
        }
    }
    
}

public class Main{
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        Agenda03 agenda = new Agenda03();
        
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
            
            }else if(ui[0].equals("agenda")){ 
                agenda.show();
                
            }else if(ui[0].equals("rmContato")){ 
                agenda.rmContato(ui[1]);
                
            }else if(ui[0].equals("rmFone")){ 
                agenda.rmFoneindice(ui[1], Integer.parseInt(ui[2]));
                
            }else if(ui[0].equals("search")){ 
                agenda.search(ui[1]);
                
            }else if(ui[0].equals("fav")){ 
                agenda.favoritar(ui[1]);
                
            }else if(ui[0].equals("favorited")){ 
                agenda.favorited();
                
            }else if(ui[0].equals("unfav")){ 
                agenda.desfavoritar(ui[1]);
                
            }else{
                System.out.println("fail: comando inválido");
            }           
        }
    }
}