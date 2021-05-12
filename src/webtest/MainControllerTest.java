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
import java.lang.Math;

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
        WebForm form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));
        
        assertEquals(resp.getURL().getPath(), "/res/department_edit");
        assertEquals(resp.getURL().getQuery().substring(0, 6), "id=-1&");
        
        form = resp.getFormWithName("add");
        assertEquals(form.getParameterValue("name"), "");
        String dep_name = "addDepartmentTestName";
        form.setParameter("name", dep_name);
     
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("submit")));  
       //departments page 
        assertEquals(resp.getURL().getPath(), "/res/departments");
        
        WebLink links[] = resp.getLinks();
        boolean added = false;
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            Department dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals(dep_name)) {
                added = true;
                break;
            }
        }
        assertTrue(added);
   }
   
   @Test
   public void addEmptyDepartmentTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));
        
        //add department
        form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("submit")));  

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
   public void addDepartmentNoTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        int deps_number = resp.getLinks().length;

        WebForm form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));

        WebLink link = resp.getLinks()[0];
        assertEquals(link.getText(), "Departments");
        assertEquals(link.getURLString(), "departments");

        resp = wc.getResponse(resp.getLinks()[0].getRequest());
        assertEquals(resp.getURL().getPath(), "/res/departments");
        assertEquals(deps_number, resp.getLinks().length);
   }


   @Test
   public void addDirectorTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));
        
        form = resp.getFormWithName("add");
        form.setParameter("name", "addDirectorTestName");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));
        
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
        resp = wc.getResponse(links[ind].getRequest());
// department info
        links = resp.getLinks();
        resp = wc.getResponse(links[links.length - 1].getRequest());
        //department edit
        
        assertEquals(resp.getURL().getPath(), "/res/department_edit");
        form = resp.getFormWithName("add");
        assertEquals(form.getParameterValue("name"), "addDirectorTestName");
        links = resp.getLinks();
        
        resp = wc.getResponse(links[1].getRequest()); //staff assignment
        assertEquals(resp.getURL().getPath(), "/res/staff_assignment");
        
        WebLink mem = resp.getLinks()[0];
        Long dir_id = Long.parseLong(mem.getParameterValues("director_id")[0]); //choosen director

        resp = wc.getResponse(mem.getRequest()); //department_edit
        form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("submit")));  
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
   
   @Test
   public void addHeadDepartmentTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        WebForm form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));

        form = resp.getFormWithName("add");
        form.setParameter("name", "addHeadDepartmentTestName");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));

        WebLink links[] = resp.getLinks();
        int ind = -1;
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        Department dep = null;
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals("addHeadDepartmentTestName")) {
                ind = i;
                break;
            }
        }

        assertTrue(ind != -1);
        resp = wc.getResponse(links[ind].getRequest());
// department info
        links = resp.getLinks();
        resp = wc.getResponse(links[links.length - 1].getRequest());
        //department edit

        assertEquals(resp.getURL().getPath(), "/res/department_edit");
        form = resp.getFormWithName("add");
        links = resp.getLinks();

        resp = wc.getResponse(links[3].getRequest()); //department assignment
        assertEquals(resp.getURL().getPath(), "/res/department_assignment");

        WebLink head = resp.getLinks()[0];
        Long head_id = Long.parseLong(head.getParameterValues("head_id")[0]); //choosen head department

        resp = wc.getResponse(head.getRequest()); //department_edit
        form = resp.getFormWithName("add");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("submit")));
        //departments

        links = resp.getLinks();
        boolean exists = false;
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getName() != null && dep.getName().equals("addHeadDepartmentTestName")) {
                assertTrue(dep.getHeadDepartment() != null);
                assertEquals(dep.getHeadDepartment().getId(), head_id);
                exists = true;
                break;
            }
        }
        assertTrue(exists);
   }
   @Test
   public void directorInfo() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
        
        WebLink links[] = resp.getLinks();
        DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
        Department dep = null;
        int ind = -1;
        for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getDirector() != null) {
                ind = i;
                break;
            }
        }
        assertTrue(ind != -1);
        resp = wc.getResponse(links[ind].getRequest());
        
        assertEquals(resp.getURL().getPath(), "/res/department_info");
        WebLink dir = resp.getLinks()[0];
        assertEquals(dir.getParameterValues("id")[0], dep.getDirector().getId().toString());

        resp = wc.getResponse(dir.getRequest());
        assertEquals(resp.getURL().getPath(), "/res/staff_info");
        
        links = resp.getLinks();
        assertEquals(links[links.length - 1].getParameterValues("id")[0], dep.getId().toString());
        
        resp = wc.getResponse(links[links.length - 1].getRequest());
        assertEquals(resp.getURL().getPath(), "/res/department_info");

   }

   @Test
   public void deleteDepartment() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");

       WebLink link = resp.getLinks()[1];
       String dep_id = link.getParameterValues("id")[0];

       resp = wc.getResponse(link.getRequest());
       assertEquals(resp.getURL().getPath(), "/res/department_info");

       WebForm del = resp.getFormWithName("delete");
       resp = wc.getResponse(del.getRequest());

       assertEquals(resp.getURL().getPath(), "/res/departments");
       WebLink links[] = resp.getLinks();
       for (int i = 1; i < links.length; i++) {
           assertTrue(!dep_id.equals(links[i].getParameterValues("id")[0]));
       }        
   }
   
   @Test
   public void headDepartmentInfo() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       Department dep = null;

       int ind = -1;
       WebLink links[] = resp.getLinks();
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            if (dep.getHeadDepartment() != null) {
                ind = i;
                break;
            }
       }

       assertTrue(ind != -1);

       resp = wc.getResponse(links[ind].getRequest());
       assertEquals(resp.getURL().getPath(), "/res/department_info");
       
       WebLink head = resp.getLinks()[dep.getDirector() == null ? 0 : 1];
       assertEquals(head.getParameterValues("id")[0], dep.getHeadDepartment().getId().toString());

       resp = wc.getResponse(head.getRequest());
       assertEquals(resp.getURL().getPath(), "/res/department_info");

   }

}
