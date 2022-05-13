import java.util.ArrayList;

public class Recette{
    protected String id;
    protected String name;
    protected ArrayList<String> in;
    protected double time;
    protected String producer;
    public Recette(String ID,String NAME,double time, ArrayList<String> in,String producer)
    {
        this.id=ID;
        this.name=NAME;
        this.time=time;
        this.producer=producer;
        this.in=in;
    }
    public String toString(){
        String A;
        int i;
        A=id+" "+name+" a des resource :";
        for(String a :in)
        {
            A=" "+A+" " +a+"\n";
        }
        A=A+" produit par --"+ producer+" -- necessitate "+time+"s"  ;
        return A;
    }

}
