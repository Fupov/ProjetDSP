import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public  class Composant {
    protected String id;
    protected String name;
    protected String category;
    private String res="";
    private static final String FILENAME = "C:\\Users\\ing\\IdeaProjects\\JavaProjet\\src\\data.xml";
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new File(FILENAME));
    NodeList list = doc.getElementsByTagName("items");

    public Composant() throws ParserConfigurationException, IOException, SAXException {


        for (int temp = 0; temp < list.getLength(); temp++)
        {
            Node node = list.item(temp);

            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                //On regarde le nom et la categorie du composant, et on les affiche
                Element element = (Element) node;
                //Quand le tag lu est unique pour l'objet, on peut faire ainsi
                this.id= element.getElementsByTagName("id").item(0).getTextContent();
                this.name= element.getElementsByTagName("name").item(0).getTextContent();

                String kri=this.id + " -- " + this.name+"\n";
                res=res+kri;
            }
        }
    }

    public String toString() {
        return res;
    }
}
