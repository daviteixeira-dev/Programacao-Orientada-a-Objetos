import java.util.Scanner;
import java.util.TreeMap;

class Aluno{
    String id_aluno;
    TreeMap<String, Disciplina> m_disp;
            
    public Aluno(String id_aluno){
         this.id_aluno = id_aluno;
         m_disp = new TreeMap<>();
    }
     
    void matricular(Disciplina disp){
        if(m_disp.containsKey(disp.id_disciplina))
            return;
        m_disp.put(disp.id_disciplina,  disp);
        disp.addDisc(this);
    }
    
    void desmatricular(Disciplina disp){
        if(!m_disp.containsKey(disp.id_disciplina))
            return;
        m_disp.remove(disp.id_disciplina);
        disp.removeDisc(this);
    }
    
    public TreeMap getDisc(){
        return m_disp;
    }
    
    public String getId(){
        return this.id_aluno;
    }
    
     
    @Override
    public String toString(){
        String out = id_aluno;
        out += "[ ";
        for(Disciplina disp : m_disp.values()){
            if(disp.getAluno() != null)
                out += disp.getId() + " " ;
        }out += " ]";
        
        return out;
    }
}

class Disciplina{
    String id_disciplina;
    TreeMap<String, Aluno> m_aluno;
    
    public Disciplina(String disciplina){
        this.id_disciplina = disciplina;
        m_aluno = new TreeMap<>();
    }
    
    void addDisc(Aluno aluno){
        if(m_aluno.containsKey(aluno.id_aluno))
            return;
        m_aluno.put(aluno.id_aluno, aluno);
        aluno.matricular(this);
    }
    void removeDisc(Aluno aluno){
        if(!m_aluno.containsKey(aluno.id_aluno))
            return;
        m_aluno.remove(aluno.id_aluno);
        aluno.desmatricular(this);
    }
    
    TreeMap getAluno(){
        return m_aluno;
    }
    
    public String getId(){
        return this.id_disciplina;
    }
    
    @Override
    public String toString(){
        //return id_disciplina;
        String out = this.id_disciplina;
        out += "[ ";
        for(Aluno aluno : m_aluno.values()){
            if(aluno.getDisc() != null)
                out += aluno.getId() + " ";
        }out += "]";
        return out;
    }
}


public class Matricula{
    TreeMap<String, Disciplina> m_disp;
    TreeMap<String, Aluno> m_aluno;
    int cont_alu = 0;
    int cont_disc = 0;
    
    public Matricula(){
        m_aluno = new TreeMap<>();
        m_disp = new TreeMap<>();
    }
    
    
    void addAluno(String id){
        if(m_aluno.containsKey(id))
            return;
        m_aluno.put(id, new Aluno(id));
        
    }
    
    void addDisc(String id){
        if(m_disp.containsKey(id))
            return;
        m_disp.put(id, new Disciplina(id));
        
    }
    
    void vincular(String aluno, String dis){
        Aluno alu = m_aluno.get(aluno);
        Disciplina disp = m_disp.get(dis);
        if(alu != null && disp != null){
            alu.matricular(disp);
        }  
    }
    
    void desmatricular(String aluno, String dis){
        Aluno alu = m_aluno.get(aluno);
        Disciplina disp = m_disp.get(dis);
        if(alu != null && disp != null)
            alu.desmatricular(disp);
    }
    
    void remove(String aluno){
        Aluno alu = m_aluno.get(aluno);
        for(Disciplina disp : m_disp.values()){
            if(alu != null){
                m_aluno.remove(alu.id_aluno);
                disp.removeDisc(alu);
            }
        }
    }
    
    void show(){
        System.out.println("Alunos: ");
        for(Aluno aluno : m_aluno.values()){
            System.out.println("    " + aluno + " ");
        }
        
        System.out.println("Disciplinas: ");
        for(Disciplina disp : m_disp.values()){
            System.out.println("    " + disp + " ");            
        }
    }

    public static void main(String[] args){
        Matricula mat = new Matricula();
        Scanner scan = new Scanner(System.in);
        while(true){
            String line = scan.nextLine();
            System.out.println("$ " + line);
            String ui[] = line.split(" ");
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("nwalu")){
                for(int i = 1; i < ui.length; i++){
                    String partes[] = ui[i].split(" ");
                    mat.addAluno(partes[0]);
                }
            }
            else if(ui[0].equals("show")){
                mat.show();
            }else if(ui[0].equals("nwdis")){
                for(int i = 1; i < ui.length; i++){
                    String partes[] = ui[i].split(" ");
                    mat.addDisc(partes[0]);
                }
            }else if(ui[0].equals("tie")){
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(" ");
                    mat.vincular(ui[1],partes[0]);
                }
            }else if(ui[0].equals("rmalu")){
                mat.remove(ui[1]);
            }else if(ui[0].equals("untie")){
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(" ");
                    mat.desmatricular(ui[1], partes[0]);
                }
            }else{
                System.out.println("fail: comando inválido");
            }
        }   
    }
}