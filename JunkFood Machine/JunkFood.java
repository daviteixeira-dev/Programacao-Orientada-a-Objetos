import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
       String nome;
       // inicializando qtd e preço com 0;
       int qtd = 0;
       float preco = 0;
       
       public Espiral(String nome , int qtd , float preco){
           this.nome = nome;
           this.qtd = qtd;
           this.preco = preco;
       }
    
        public String toString() {
            return "[" + this.nome + " : " + this.qtd + " U " + ":" + this.preco + " RS" + "]";
	}
}

class Maquina{
    float saldo = 0;
    float lucro = 0;
    ArrayList<Espiral> espirais;
    int espira;
    int maxProd; 
    
        public Maquina(int qtdEspirais, int maxProdutos){
		this.espirais = new ArrayList<Espiral>();
		for(int i = 0; i < qtdEspirais; i++)
			this.espirais.add(new Espiral("-",0,0));
	}
        public boolean alteraEspiral(int indice,String nome,int qtd,float preco){
            if(indice > (espirais.size() - 1)){
               return false;
            }
            if(qtd < 0 || qtd > this.maxProd){
                //return this.espirais.get(indice) = new Espiral(nome,qtd,preco);
                this.espirais.get(indice).nome = nome;
                this.espirais.get(indice).qtd = qtd;
                this.espirais.get(indice).preco = preco;
                
                return true;
        }
	
        public boolean remove(int indice){
            if(indice > (espirais.size() - 1)){
                return false;
            }
            this.espirais.get(indice).nome = "-";
            this.espirais.get(indice).qtd = 0;
            this.espirais.get(indice).preco = 0;     
            
            return true;    
                
    }
         public boolean inserirDinheiro(float value){
             saldo += value;
             return true;
         }
         
         public float getSaldo(){
             return saldo;
         }
            
        public float pedirTroco(){
            float troco = saldo;
            saldo = troco - saldo;
            return troco;
        }
        
        public boolean vender(int indice){
            //se o indice for inválido, ele nao consegue comprar;
            if(indice >(espirais.size() - 1)){
                return false;
            }
           for(int i = 0; espirais.size(); i++){
               Espiral espi = espirais.get(i);
                if(espi == null){
                    return false;
                }
                else
                   if(espi == espirais.get(indice)){
                       if(espi.qtd > 0){
                           if(saldo >= espi.preco){
                               espi.qtd = espi.qtd - 1;
                               saldo = saldo - espi.preco;
                               System.out.println("Comprou um " +espi.nome + " saldo" + saldo);
                               return true;
                           }
                       }
                   } 
                else
                    if(saldo < espi.preco){
                        System.out.println("saldo insuficiente");
                        return false;
                    }
                    else{
                        System.out.println("Produto não disponível");
                        return false;
                    }
           }
            
        
        
       public String toString() {
		String saida = " ";
		for(int i = 0; i < espirais.size(); i++)
			saida += i + " " + espirais.get(i) + "\n";
		return saida;
	}
}

// Controlador do professor e do meu colega Gabriel;

class Controller{
    Maquina maq;
    static final int DEFAULT_ESPIRAIS = 3;
    static final int DEFAULT_MAX = 5;
    public Controller() {
        maq = new Maquina(DEFAULT_ESPIRAIS, DEFAULT_MAX);
    }
    
    private float toFloat(String s) {
        return Float.parseFloat(s);
    }
    
    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "show, init _espirais _maximo, set _indice _nome _qtd __preço, remover _indice, dinheiro _valor, saldo _valor,comprar _indice troco";
        else if(ui[0].equals("init"))
            maq = new Maquina(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
        else if(ui[0].equals("show"))
            return "Saldo: " + maq.getSaldo() + "\n" + maq;
        else if(ui[0].equals("set"))
        	maq.alterarEspiral(Integer.parseInt(ui[1]), (ui[2]), Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
        else if(ui[0].equals("remover"))
        	maq.remover(Integer.parseInt(ui[1]));
        else if(ui[0].equals("dinheiro"))
        	maq.inserirDinheiro(Float.parseFloat(ui[1]));
        else if(ui[0].equals("saldo"))
        	return "" + maq.getSaldo();
        else if(ui[0].equals("troco"))
        	return "Você recebeu " + maq.pedirTroco();
        else if(ui[0].equals("comprar")) {
        	maq.vender(Integer.parseInt(ui[1]));
        }
        else
            return "comando invalido";
        return "done";
    }
}

public class Junkfood {
	    static Scanner scan = new Scanner(System.in);
	    
	    static private String tab(String text){
	        return "  " + String.join("\n  ", text.split("\n"));
	    }
	    
	    public static void main(String[] args) {
	        Controller cont = new Controller();
	        System.out.println("Digite um comando ou help:");
	        while(true){
	            String line = scan.nextLine();
	            try {
	                System.out.println(tab(cont.oracle(line)));
	            }catch(Exception e) {
	                System.out.println(tab(e.getMessage()));
	            }
	        }
	    }
}