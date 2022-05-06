import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OtherCentralLink extends Exception {
    private String  Message;
    public OtherCentralLink(String Message) throws ParserConfigurationException, IOException, SAXException {
        this.Message=Message;
    }
    public String toString(){
        return Message;
    }
}
