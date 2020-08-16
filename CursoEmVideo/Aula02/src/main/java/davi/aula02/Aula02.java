package davi.aula02;

public class Aula02 {
    public static void main(String[] args) {
        //instanciar
        Caneta c1 = new Caneta();
        Caneta c2 = new Caneta();
        
        //referencia aos atributos
        c1.cor = "Azul";
        c1.ponta = 0.5f;
        
        c2.modelo = "Hostnet";
        c2.cor = "Preta";
        
        //referencia aos metodos
        c1.tampar();
        c1.rabiscar();
        c1.status();
        
        c2.status();
        c2.destampar();
        c2.rabiscar();
        
    }
}
