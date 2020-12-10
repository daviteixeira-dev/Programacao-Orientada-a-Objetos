package davi.agenciabancaria01;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.Scanner;

class Operacao{
    
    private String tipo;
    private float valor;
    private float saldo;
    private int indice ;

    public Operacao(int indice, String tipo, float valor, float saldo){
        this.indice = indice;
        this.tipo = tipo;
        if(tipo.equals("saque")){
            valor *= -1;
        }
        this.valor = valor;
        this.saldo = saldo;
        this.indice += 1;
    }
        
    public String toString(){
        return this.indice +": "+tipo+": "+(int)valor+": "+(int)saldo;
    }

    public String getTipo() {
        return this.tipo;
    }
    
    public float getTarifa() {
        return this.valor;
    }
}

class Conta{
    private int numero;
    private float saldo;
    private ArrayList<Operacao> extrato;

    public Conta(int numero){
        this.numero = numero;
        this.saldo = 0;
        this.extrato = new ArrayList<Operacao>();
        extrato.add(new Operacao(extrato.size()-1,"abertura",0,0));
    }

    public String toString(){
        return
            "conta:" + getNumero()+" saldo:"+ (int)getSaldo()+"\n";
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public float getSaldo(){
        return this.saldo;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    public void sacar(float valor){
        if(this.saldo - valor < 0){
            System.out.println("fail: saldo insuficiente");
        }else{
            this.saldo -= valor;
            extrato.add(new Operacao(extrato.size()-1,"saque", valor, this.saldo));
        }
    }

    public void deposito(float valor){
        if(valor < 0)
             System.out.println("fail: valor invalido");
        else{
            this.saldo += valor;
            extrato.add(new Operacao(extrato.size()-1,"deposito", valor, this.saldo));
        }
    }

    public void tarifa(float valor){
        this.saldo -= valor;
        extrato.add(new Operacao(extrato.size()-1,"tarifa", valor *= -1, this.saldo));
    }

    public void extrato(){
        for(Operacao op : extrato){
            System.out.println(op.toString());
        }
    }

    public void extratoParcial(int aux){
        for(int i = extrato.size();i > extrato.size() - aux;i--){
            System.out.printf(extrato.get(i).toString());
        }
    }

    public void extorno(int indice){
        if(indice < 0 || indice > extrato.size() ){
            System.out.printf("fail: indice "+ indice +" invalido");
        }else if(extrato.get(indice).getTipo() != "tarifa"){
            System.out.printf("fail: indice "+ indice +" não é tarifa");
        }else{
            saldo += extrato.get(indice).getTarifa();
        }
    }
}

class Main{
    public static void main(String[] args){
        
        Conta conta = new Conta(0);
        Scanner ler = new Scanner(System.in);
        
        while (true){
            
            String comando = ler.nextLine();
            String[] vetor = comando.split(" ");
            System.out.println("$"+ comando);
            
            if(vetor[0].equals("show")){
                System.out.printf(conta.toString());
            }else if(vetor[0].equals("init")){
                conta = new Conta(Integer.parseInt(vetor[1]));
            }else if(vetor[0].equals("deposito")){
                conta.deposito(Float.parseFloat(vetor[1]));
            }else if(vetor[0].equals("saque")){
                conta.sacar(Float.parseFloat(vetor[1]));
            }else if(vetor[0].equals("tarifa")){
                conta.tarifa(Float.parseFloat(vetor[1]));
            }else if(vetor[0].equals("extrato")){
                conta.extrato();
            }else if(vetor[0].equals("extratoN")){
                conta.extratoParcial(Integer.parseInt(vetor[1]));
            }else if(vetor[0].equals("extornar")){
                for(int i = 1; i < vetor.length; i++){
                    conta.extorno(Integer.parseInt(vetor[i]));
                }
            }else{
               break;
            }
        }
    }
}