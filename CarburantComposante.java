public class CarburantComposante extends Composant{
    protected boolean fuel;
    protected String categorie;
    protected int value;
    public CarburantComposante ()
    {
        super();
        this.categorie="";
        this.fuel=false;
        this.value=0;
    }
    public CarburantComposante (String ID ,String NAME,Boolean F,String categorie,int value)
    {
        super(ID, NAME,categorie);
        this.categorie=categorie;
        this.fuel=F;
        this.value=value;
    }
    public String toString(){
        return super.toString()+" fuel :"+fuel+ " "+categorie+" "+value;
    }

}
