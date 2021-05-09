package webtest;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.*;
import com.meterware.httpunit.WebResponse;
import junit.framework.*;
import org.junit.Test; 
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;

import DAO.*;
import logic.*;
import DAO.Factory;

import java.sql.SQLException;

public class MainControllerTest {
    @Test
    public void mainContentTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://127.0.0.1:8080/res/");
        assertEquals("Main page", resp.getTitle());
        WebLink[] links = resp.getLinks();
        assertEquals(links.length, 2);
        assertEquals(links[0].getText(), "Departments");
        assertEquals(links[1].getText(), "Staff Members");
        assertEquals(links[0].getURLString(), "departments");
        assertEquals(links[1].getURLString(), "staff");
   }
   
   @Test
   public void mainLinksTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://127.0.0.1:8080/res/");
        WebLink[] links = resp.getLinks();
        
        WebResponse deps = links[0].click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/res/departments").getURL().getPath(), deps.getURL().getPath());
        WebResponse staff = links[1].click();
        assertEquals(wc.getResponse("http://127.0.0.1:8080/res/staff").getURL().getPath(), staff.getURL().getPath());
        
   }
   
   @Test
   public void departmentsContentTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        assertEquals("Departments page", resp.getTitle());
        WebLink links[] = resp.getLinks();
        assertEquals(links[0].getText(), "Main");
        assertEquals(links[0].getURLString(), "/res/");
        WebForm forms[] = resp.getForms();
        assertEquals(forms.length, 2);
        assertEquals(forms[0].getAction(), "/res/add_department");
        assertEquals(forms[1].getAction(), "/res/filter_departments");
   }

   @Test
   public void addDepartmentTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm forms[] = resp.getForms();
        resp.close();
        resp = forms[0].submit();
        
        assertEquals(resp.getURL().getPath(), "/res/department_edit");
        assertEquals(resp.getURL().getQuery().substring(0, 6), "id=-1&");
        forms = resp.getForms();
        assertEquals(forms[0].getParameterValue("name"), "");
        forms[0].setParameter("name", "addDepartmentTestName");
        
        resp.close();
        resp = forms[0].submit();
        
        assertEquals(resp.getURL().getPath(), "/res/departments");
        WebLink links[] = resp.getLinks();
        boolean added = false;
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            Department dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals("addDepartmentTestName")) {
                added = true;
                break;
            }
        }
        assertTrue(added);
        resp.close();
   }
  /* 
   @Test
   public void addEmptyDepartmentTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm forms[] = resp.getForms();
        resp = forms[0].submit();
        
        //add department
        forms = resp.getForms();
        resp = forms[0].submit();

        //departments
        WebLink links[] = resp.getLinks();
        boolean added = false;
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            Department dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() == null) {
                added = true;
                break;
            }
        }
        assertTrue(added);
   }

   @Test
   public void addDirectorTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm forms[] = resp.getForms();
        resp = forms[0].submit();
        
        forms = resp.getForms();
        resp = forms[0].submit();
        forms[0].setParameter("name", "addDirectorTestName");
        resp = forms[0].submit();
        
        WebLink links[] = resp.getLinks();
        int ind = -1;
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        Department dep = null;
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals("addDirectorTestName")) {
                ind = i;
                break;
            }
        }

        assertTrue(ind != -1);
        resp = links[ind].click();
// department info
        links = resp.getLinks();
        resp = links[links.length - 1].click();
        //department edit
        assertEquals(resp.getURL().getPath(), "/res/department_edit");
        forms = resp.getForms();
        assertEquals(forms[0].getParameterValue("name"), "addDirectorTestName");
        links = resp.getLinks();
        resp = links[1].click(); //staff assignment
        assertEquals(resp.getURL().getPath(), "/res/staff_assignment");
        WebLink mem = resp.getLinks()[0];
        Long dir_id = Long.parseLong(mem.getParameterValues("mem_id")[0]); //choosen director
        resp = mem.click(); //department_edit
        forms = resp.getForms();
        resp = forms[0].submit();
        //departments
        
        links = resp.getLinks();
        boolean exists = false;
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals("addDirectorTestName")) {
                assertTrue(dep.getDirector() != null);
                assertEquals(dep.getDirector().getId(), dir_id); 
                exists = true;
                break;
            }
        }

        assertTrue(exists);

        
   }

   */
}
