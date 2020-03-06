/* Programa para simulação de uma conta Bancaria */

public class Main {
    public static void main(String[] args) {
        Titular Davi = new Titular("Davi", "06005180222");
        Conta conta1 = new Conta(Davi);
        
        System.out.println(Davi == conta1.titular);
        
    }
}