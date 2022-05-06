public class Composant {
    protected String id;
    protected String name;
    public Composant(String ID ,String NAME){
        this.id=ID;
        this.name=NAME;

    }
    public Composant()
    {
        this.id="";
        this.name="";
    }
    public String toString()
    {
        return " "+name;
    }
    public String getid() {
        return id ;
    }
    public String getname(){
        return name;
    }

}
