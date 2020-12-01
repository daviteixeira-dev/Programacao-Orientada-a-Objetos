package davi.saladecinema;

/* @author Davi Teixeira */

import java.util.Scanner;
import java.util.ArrayList;

class Cliente {
    
    private String id;
    private String fone;

    public Cliente(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

    @Override
    public String toString() {
        return getId() +":"+ getFone();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public boolean compararID(String id){
        if(id.equals(this.id)){
            return true;
        }
        return false;
    }
}

class Cinema{
    
    ArrayList<Cliente> cadeiras = new ArrayList<Cliente>();

    public Cinema(int qtd) {
        for(int i = 0; i < qtd; i++){
            cadeiras.add(new Cliente(null, null));
        }
    }
    
    public void reservar(String id, String fone, int indice) {
        if(indice >= (cadeiras.size())){
            System.out.println("esse lugar não existe");
            return;
        }else if(cadeiras.get(indice).getId() != null){
            System.out.println("failure: cadeira ja esta ocupada");
            return;
        }
        else{
            for(Cliente cliente : cadeiras){
                if(id.equals(cliente.getId())){
                    System.out.println("failure: cliente ja esta no cinema");
                    return;
                }
            }
        }
        Cliente x = new Cliente(id, fone);
        cadeiras.set(indice, x);
    }

    public void cancelar(String id) {
        boolean bandeira = false;
        int i = 0;
        for(Cliente cliente : cadeiras){
            if(cliente.compararID(id)){
                Cliente x = new Cliente(null, null);
                cadeiras.set(i, x);
                bandeira = true;
            }
            i++;
        }
        if(!bandeira){
            System.out.println("failure: cliente nao esta no cinema");
        }
    }

    public void MostrarCadeiras() {
        System.out.printf("[ ");
        for(Cliente cliente : cadeiras){
            if(cliente.getId() != null){
                System.out.printf(cliente.toString()+" ");
            }else{
                System.out.printf("- ");
            }
        }
        System.out.println("]");
    }
}

public class Main{
    public static void main(String[] args){
        
        Cinema cinema = new Cinema(0);
        Scanner ler = new Scanner(System.in);
        
        while(true){
            
            String comando = ler.nextLine();
            String[] partes = comando.split(" ");
            System.out.println("$"+ comando);

            if(partes[0].equals("init")){
                cinema = new Cinema(Integer.parseInt(partes[1]));
            }else if(partes[0].equals("show")){
                cinema.MostrarCadeiras();
            }else if(partes[0].equals("reservar")){
                cinema.reservar(partes[1], partes[2], Integer.parseInt(partes[3]));
            }else if(partes[0].equals("cancelar")){
                cinema.cancelar(partes[1]);
            }else{
                break;
            }
        }
    }
}