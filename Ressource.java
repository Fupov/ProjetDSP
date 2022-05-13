public class Ressource extends Composant{
    private String minedby;
    public Ressource(String ID)
    {
        this.id=ID;
    }
    public Ressource(String ID,String NAME,String minedby)
    {
        super(ID, NAME);
        this.minedby=minedby;
    }
    public Ressource(String ID,String NAME,String category,String minedby)
    {
        super(ID, NAME,category);
        this.minedby=minedby;
    }
    public String toString() {
        return super.toString()+ " mined by " + minedby;

    }
}
