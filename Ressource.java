public class Ressource extends Composant{
    private Extracteur minedby;
    public Ressource()
    {
        super();
        this.minedby=new Extracteur();
    }
    public Ressource(String ID,String NAME,int speed)
    {
        super(ID, NAME);
        this.minedby=new Extracteur(speed);
    }
    public String toString() {
        return super.toString()+ " mined by " + minedby;

    }
}
