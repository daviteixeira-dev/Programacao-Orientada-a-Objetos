public class Conta {
    // atributos
    public Titular titular;
    public float saldo;
    
    //contrustor
    
    public Conta(Titular titular){
        this.saldo = 0;
        this.titular = titular;
    }
    
    // metodos
    
    public boolean sacar(float quantidade){
        if(quantidade >= 0 && this.saldo >= quantidade){
            this.saldo -= quantidade;
            return true;
        }
        return false;
    }
    
    public boolean depositar(float quantidade){
        if(quantidade > 0){
            this.saldo += quantidade;
            return true;
        }
        return false;
    }
    
    public boolean transferirPara(Conta outraConta, float quantidade){
        if(quantidade <= this.saldo && quantidade > 0){
            outraConta.saldo += quantidade;
            this.saldo -= quantidade;
            return true;
        }
        return false;
    }
}