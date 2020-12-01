package davi.shell;

import java.util.Scanner;

/* @author Davi Teixeira */

public class Shell {
    public static void main(String[] args){
        
        Double acomulador = 0.0;
        
        Scanner scanner = new Scanner(System.in);

        do{
                
            String linha = scanner.nextLine();
                
            String[] comandos = linha.split(" ");
            System.out.println("$"+ linha);
                
            if(comandos[0].equals("end")){
                break;
                    
            }else if(comandos[0].equals("show")){
                System.out.printf("%.2f \n", acomulador);
                    
            }else if(comandos[0].equals("add")){
                acomulador += Double.parseDouble(comandos[1]);
                    
            }else if(comandos[0].equals("mult")){
                acomulador *= Double.parseDouble(comandos[1]);
                    
            }else if(comandos[0].equals("div")){
                if(Double.parseDouble(comandos[1]) != 0){
                    acomulador /= Double.parseDouble(comandos[1]);
                }
                else{
                    System.out.println("fail: division by zero");
                }

            }else if(comandos[0].equals("addm")){
                for(int i = 1; i < comandos.length; i++){
                    acomulador += Double.parseDouble(comandos[i]);
                }
            }	
        }while(true);
    }
}