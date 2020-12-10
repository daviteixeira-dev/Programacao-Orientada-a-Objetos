package davi.agiotagentefina;

/* @author Davi Teixeira */

import java.util.ArrayList;
import java.util.Scanner;

class Transacao{
    public int id = 0;
    public float valor = 0;
    public String codename;

    public Transacao(int id, String codename ,float valor){
        this.id = id;
        this.valor = valor;
        this.codename = codename;
    }

    @Override
    public String toString(){
        return "- " + "id:" + this.id + " " + this.codename + ":" + (int)valor;
    }
}

class Cliente{
    
    public String codename;
    public float  limite = 0;
    public float  balance;
    
    public Cliente(String codename, float limite){
        this.codename = codename;
        this.limite = limite;
        this.balance = 0;
    }
    
    @Override
    public String toString() {
        return "- " + this.codename + ":" + (int)balance + "/" + (int)limite;
    }
}

class Agiota{
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Transacao> transacoes = new ArrayList<>();
    private float balance;
    private int nextTransacaoId;

    public Agiota(float saldo){
        this.balance = saldo;
        this.nextTransacaoId = 0;
    }
    
    void addClientes(String codename, float limite){
	    for(Cliente c : clientes)
            if(c.codename.equals(codename)){
		        throw new RuntimeException("fail: cliente ja existe");
            }
	    Cliente novo = new Cliente(codename, limite);
            clientes.add(novo);
    }
    
    Cliente findCliente(String codename){
        for(Cliente cliente : clientes){
            if (cliente.codename.equals(codename))
                return cliente;
        }
        throw new RuntimeException("fail: cliente nao existe");
    }
    
    void addTransacao(String codename, float valor){
        Transacao tran = new Transacao(nextTransacaoId, codename, valor);
        this.transacoes.add(tran);
        nextTransacaoId += 1;
    }
    
    void emprestar(String codename, float valor){
        Cliente cliente = findCliente(codename);
        
        if(valor > this.balance){
            throw new RuntimeException("fail: fundos insuficientes");
            
        }else if(cliente == null){
            throw new RuntimeException("fail: cliente nao existe");
            
        }else if(cliente.balance + valor > cliente.limite){
            throw new RuntimeException("fail: limite excedido");
            
        }else if(this.balance > valor){
            addTransacao(codename, valor);
            this.balance -= valor;
            cliente.balance += valor;
        }
    }
    
    void receber(String codename, float saldo){
        Cliente cliente = findCliente(codename);
        if(cliente.balance < saldo){
            throw new RuntimeException("fail: valor maior que a divida");
        }
        addTransacao(codename, -saldo);
        cliente.balance -= saldo;
        this.balance += saldo;
    }
    
    public void DellDividas(String codename){
        for(int i = 0; i<transacoes.size(); i++){
            this.transacoes.remove(transacoes.get(i));
        }
    }
    
    public void matar(String codename){
        for( int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).codename.equals(codename)){
                this.clientes.remove(clientes.get(i));
                DellDividas(codename);
		return;
            }
        }
    }
    
    String getHistorico(){
        String saida = "transactions:\n";
        for(Transacao tr : transacoes)
            saida += tr + "\n";
        saida += "balance:" + (int)balance;
        return saida;
    }

    @Override
    public String toString(){
        
        String saida_clientes = "clients:\n";
        for(Cliente cliente : clientes)
           saida_clientes += cliente + "\n";
        System.out.print(saida_clientes);
        return getHistorico();
    }
}

public class Controller{
    
    public static void main(String[] args){
        
        Agiota agiota = new Agiota(0);
        Scanner scanner = new Scanner(System.in);
        
        do{
            String line = scanner.nextLine();
            
            String[] ui = line.split(" ");
            System.out.println("$"+line);
            
            try{
                if(ui[0].equals("end")){
                    break;
                }else if(ui[0].equals("init")){
                    agiota = new Agiota(Integer.parseInt(ui[1]));
                }else if(ui[0].equals("show")){
                    System.out.println(agiota);
                }else if(ui[0].equals("lend")){
                    agiota.emprestar(ui[1], Integer.parseInt(ui[2]));
                }else if(ui[0].equals("receive")){
                    agiota.receber(ui[1], Integer.parseInt(ui[2]));
                }else if(ui[0].equals("addCli")){
                    agiota.addClientes(ui[1], Integer.parseInt(ui[2]));
                }else if(ui[0].equals("kill")){
                    agiota.matar(ui[1]);
                }else{
                    System.out.println("fail: comando invalido");
                }
            }catch(RuntimeException re){
                System.out.println(re.getMessage());
            }
        }while(true);
    }
}