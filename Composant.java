public class Composant {
    protected String id;
    protected String name;
    protected String categorie;
    public Composant(String ID ,String NAME, String categorie){
        this.id=ID;
        this.name=NAME;
        this.categorie=categorie;
    }
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
        return id+" -- "+name;
    }
    public String getid() {
        return id ;
    }
    public String getname(){
        return name;
    }

}
