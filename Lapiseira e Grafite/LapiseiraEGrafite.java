package davi.lapiseiraegrafite;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.Scanner;

class Lapiseira{

    private double ponta;
    ArrayList<Grafite> cargas;
    
    public Lapiseira(double ponta){
        this.ponta = ponta;
        this.cargas = new ArrayList<>();
        this.cargas.add(new Grafite(ponta, 4));
    }

    public ArrayList getCargas(){
        return cargas;
    }

    public void setCargas(ArrayList<Grafite> cargas){
        this.cargas = cargas;
    }

    public double getPonta(){
        return ponta;
    }

    public void escrever(){
        if(cargas.get(0).getDurabilidade() > 0 && this.ponta > 0){
            this.cargas.get(0).usarGrafite();
        }else if (this.ponta <= 0){
            System.out.println("fail: tamanho de ponta inválido");
        }else{
            System.out.println("fail: lapiseira vazia");
        }
    }

    public void colcarGrafite(double pontaGrafite, int durabilidade){
        Grafite carga = new Grafite(pontaGrafite, durabilidade);
        if (pontaGrafite == this.ponta && carga.getDurabilidade() == 0){
            System.out.println("Você colocou o grafite");
        }else if (carga.getDurabilidade() > 0){
            System.out.println("fail: lapiseira ainda carregada");
        }else{
            System.out.println("fail: grafite de expessura errada");
        }
    }

}

class Grafite{

    private double ponta;
    private int durabilidade;

    public Grafite(double ponta, int durabilidade){
        this.ponta = ponta;
        this.durabilidade = durabilidade;
    }

    public int getDurabilidade(){
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade){
        this.durabilidade = durabilidade;
    }

    public double getPonta(){
        return ponta;
    }

    public void usarGrafite(){
        if (this.durabilidade > 0) {
            System.out.println("Você está escrevendo");
            this.durabilidade--;
        }
    }

}

public class Controller {

    public static void main(String[] args){
        
        Lapiseira lapiseira = new Lapiseira(0);
        Scanner captInfor = new Scanner(System.in);
        
        do{
            String vetCapInfor[] = (captInfor.nextLine()).split(" ");
            
            if(vetCapInfor[0].equals("end")){
                break;
            }else if(vetCapInfor[0].equals("init")){
                lapiseira = new Lapiseira(Double.parseDouble(vetCapInfor[1]));
            }else if(vetCapInfor[0].equals("show")){
                
            }else if(vetCapInfor[0].equals("write")){
                lapiseira.escrever();
            }else if(vetCapInfor[0].equals("inserir")){
                lapiseira.colcarGrafite(Double.parseDouble(vetCapInfor[1]), Integer.parseInt(vetCapInfor[2]));
            }else if(vetCapInfor[0].equals("remover")){
                
            }else{
                    System.out.println("fail: comando invalido");
            }
        }while(true);
    }
}