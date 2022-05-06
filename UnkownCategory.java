import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class UnkownCategory extends Exception {
    private String  Message;
    public UnkownCategory(String Message) throws ParserConfigurationException, IOException, SAXException {
        this.Message=Message;
    }
    public String toString(){
        return Message;
    }
}
