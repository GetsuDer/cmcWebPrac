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
    public void indexContentTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/");
        assertEquals("Main page", resp.getTitle());
        WebLink[] links = resp.getLinks();
        assertEquals(links.length, 2);
        assertEquals(links[0].getText(), "Departments");
        assertEquals(links[1].getText(), "Staff Members");
        assertEquals(links[0].getURLString(), "departments");
        assertEquals(links[1].getURLString(), "staff");
   }
   
   @Test
   public void indexLinksTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/");
        WebLink[] links = resp.getLinks();
        
        WebResponse deps = links[0].click();
        assertEquals("Departments page", deps.getTitle());
        WebResponse staff = links[1].click();
        assertEquals("Staff members page", staff.getTitle());
        
   }
   
}
