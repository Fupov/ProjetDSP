public class CarburantRessource extends Ressource {
    protected boolean fuel;
    protected String categorie;
    protected int value;
    public CarburantRessource ()
    {
        super();
        this.categorie="";
        this.fuel=false;
        this.value=0;
    }
    public CarburantRessource (String ID ,String NAME,int speed ,Boolean F,String categorie,int value)
    {
        super(ID, NAME,speed);
        this.categorie=categorie;
        this.fuel=F;
        this.value=value;
    }
    public String toString(){
        return super.toString()+" fuel :"+fuel+ " "+categorie+" "+value;
    }

}
