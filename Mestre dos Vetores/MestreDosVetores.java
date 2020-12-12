import java.util.ArrayList;
import java.util.Scanner;


public class MestreDosVetores {

    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ArrayList<Integer> vetor = new ArrayList<Integer>();
        
        while(true){
        
            String linha = scan.nextLine();
            System.out.println("$" + linha);
            String ui[] = linha.split(" ");
            String cmd = ui[0];
            
                        if(linha.equals("end")){
				break;
			}
                        else if(cmd.equals("show")){
				System.out.print("[ ");
				for(Integer valor : vetor)
					System.out.print(valor + " ");
				System.out.print("]\n");
			}
                        else if(cmd.equals("rshow")){
				System.out.print("[ ");
				for(int i = vetor.size() - 1; i >= 0; --i)
					System.out.print(vetor.get(i) + " ");
				System.out.print("]\n");
				
			}
                        else if(cmd.equals("add")){
				for(int i = 1; i < ui.length; i++)
					vetor.add(Integer.parseInt(ui[i]));
			}
                        else if(cmd.equals("find")){
				
				System.out.print("[ ");
				for(int i = 1; i < ui.length; i++)
					System.out.print(vetor.indexOf(Integer.parseInt(ui[i])) + " ");
				System.out.print("]\n");
				
			}else if(cmd.equals("get")){
				
				int ind = Integer.parseInt(ui[1]);
				System.out.println(vetor.get(ind));
				
			}else if(cmd.equals("set")){
				
				int ind = Integer.parseInt(ui[1]);
				int value = Integer.parseInt(ui[2]);
				vetor.set(ind, value);
				
			}else if(cmd.equals("ins")){
				
				int ind = Integer.parseInt(ui[1]);
				int value = Integer.parseInt(ui[2]);
				if(ind < 0)
					continue;
				if(ind > vetor.size())
					ind = vetor.size();
				vetor.add(ind, value);
				
			}else if(cmd.equals("rmi")){
				
				int ind = Integer.parseInt(ui[1]);
				if((ind < 0) || (ind >= vetor.size()))
					System.out.println("fail");
				else
					vetor.remove(Integer.parseInt(ui[1]));
				
			}else if(cmd.equals("rma")){
				
				int value = Integer.parseInt(ui[1]);
			    for (int i = 0; i < vetor.size(); i++) {
			        if (value == vetor.get(i)) {
			            vetor.remove(i);
			            i--;
			        }
			    }   
			}else{
				System.out.print("fail: command not found\n");
			}
        }
    }
}