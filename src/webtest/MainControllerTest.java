package webtest;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.*;
import com.meterware.httpunit.WebResponse;
import junit.framework.*;
import org.junit.Test; 
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;

public class MainControllerTest {
    @Test
    public void mainTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/");
        assertEquals("", "");
   }
}
