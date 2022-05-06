public class Recette extends Composant {
    protected Ressource[] in;
    protected int time;
    protected Usine producer;

    public Recette()
    {
        super();
        this.time=0;
        this.producer=new Usine();
        this.in=new Ressource[1];
    }
    public Recette(int n,String ID ,String NAME,int time,float speed,int usage,int drain)
    {

        super(ID,NAME);
        int i;
        this.time=time;
        this.producer=new Usine(speed,usage,drain);
        this.in=new Ressource[n];
        for(i=0;i<n;i++)
        {
            in[i]=new Ressource();
        }
    }
    public String toString(){
        String A;
        int i;
        A=super.toString()+" a des resource :";
        for(i=0;i<in.length;i++)
        {
            A=" "+A+" " +(in[i].toString())+"\n";
        }
        A=" product par"+ producer+"necessitate "+time+"s"  ;
        return A;
    }

}
