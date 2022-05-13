public class RecetteMulti {
    private Ressource[] out;

    public RecetteMulti(){
        super();
        this.out=new Ressource[1];
    }
    public RecetteMulti(int n,String ID ,String NAME,String category,int time,float speed,int usage,int drain, Ressource[] out)
    {
        //super(n,ID,NAME,category,time,speed,usage,drain);
        this.out=new Ressource[n];
        for(int i=0;i<n;i++)
        {
            // out[i]=new Ressource(id);
        }
    }

    public String toString(){
        String A;
        int i;
        A=super.toString()+" a des resource in:";
        /*for(i=0;i<in.length;i++)
        {
            A=" "+A+" " +(in[i].toString())+"\n";
        }
        A=A+" product par"+ producer+"necessitate "+time+"s"  ;*/
        return A;
    }

}
