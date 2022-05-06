import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class UsageException extends Exception{
    private String  Message;
    public UsageException(String Message) throws ParserConfigurationException, IOException, SAXException {
        this.Message=Message;
    }
    public String toString(){
        return Message;
    }
}
