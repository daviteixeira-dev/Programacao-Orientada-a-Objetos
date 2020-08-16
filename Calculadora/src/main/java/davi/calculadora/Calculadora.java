package davi.calculadora;

public class Calculadora{
    //atributos
    public float registrador;
	
    //construtor
    public Calculadora() {
	this.registrador = 0;
    }
    
    //metodos
    public void add(float valor) {
	this.registrador += valor;
    }
	
    public void mult(float valor) {
	this.registrador *= valor;
    }
	
    public void div(float valor) {
	this.registrador /= valor;
    }
	
    public float show() {
	return this.registrador;	
    }
	
    public void adm(float[] valores) {
	for(int i = 0; i < valores.length; i++) {
            this.registrador += valores[i];
	}
    }
}