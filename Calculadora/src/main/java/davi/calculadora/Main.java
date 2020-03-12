package davi.calculadora;

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
	String linha;
	Scanner scanner = new Scanner(System.in);
	Calculadora calc = new Calculadora();

	do{
            linha = scanner.nextLine();
            String[] comandos = linha.split(" ");
			
            if(comandos[0].equals("end")){
		break;
            }else if(comandos[0].equals("show")){
		System.out.println(calc.show());
                
            }else if(comandos[0].equals("add")){
		float valor = Float.parseFloat(comandos[1]);
		calc.add(valor);
                
            }else if(comandos[0].equals("add")){
		float valor = Float.parseFloat(comandos[1]);
		calc.mult(valor);
                
            }else if(comandos[0].equals("add")){
                float valor = Float.parseFloat(comandos[1]);
                
                if(valor != 0){
                    calc.div(valor);
		}else{
                    System.out.println("ERROR!!");
		}
                
            }else if(comandos[0].equals("adm")){
                float[] parametros = new float[comandos.length - 1];
				
                for(int i = 1; i < comandos.length; i++) {
                    parametros[i -1] = Float.parseFloat(comandos[i]);
                }
                calc.adm(parametros);
            }
			
	}while(true);
    }
}