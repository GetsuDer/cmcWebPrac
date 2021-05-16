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
import java.util.Collection;
import java.util.ArrayList;

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
   public void seeDirectorInfo() throws IOException, SAXException, SQLException {
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
   public void seeHeadDepartmentInfo() throws IOException, SAXException, SQLException {
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
   
   @Test
   public void seeSubDepartments() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       Department dep = null;
       int ind = -1;
       WebLink links[] = resp.getLinks();
       Collection<Department> subs = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            subs = dao.getSubDepartments(dep);
            if (subs.size() > 0) {
                ind = i;
                break;
            }
       }

       assertTrue(ind != -1);
       
       resp = wc.getResponse(links[ind].getRequest());
       int subDepsStart = (dep.getDirector() == null ? 0 : 1) + (dep.getHeadDepartment() == null ? 0 : 1);
       links = resp.getLinks();
       for (int i = 0; i < subs.size(); i++) {
           String id = links[i + subDepsStart].getParameterValues("id")[0];
           Department sub = dao.getDepartmentById(Long.parseLong(id));
           boolean contained = false;
           for (Department d : subs) {
               if (d.getId() == sub.getId()) {
                   contained = true;
                   break;
               }
           }
           assertTrue(contained);
       }

   }

   @Test
   public void seeDepartmentPositions() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       PositionDAO posDao = Factory.getInstance().getPositionDAO();
       Department dep = null;
       int ind = -1;
       WebLink links[] = resp.getLinks();
       Collection<Position> poss = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            poss = posDao.getPositionsByDepartment(dep);
            if (poss.size() > 0) {
                ind = i;
                break;
            }
       }

       assertTrue(ind != -1);

       resp = wc.getResponse(links[ind].getRequest());
       Collection<Department> subs = dao.getSubDepartments(dep);
       int posShift = (dep.getDirector() == null ? 0 : 1) + (dep.getHeadDepartment() == null ? 0 : 1) + subs.size();
       
       links = resp.getLinks();
       for (int i = 0; i < poss.size(); i++) {
           String id = links[i + posShift].getParameterValues("id")[0];
           Position pos = posDao.getPositionById(Long.parseLong(id));
           posShift += pos.getSize();
           boolean contained = false;
           for (Position p : poss) {
               if (p.getId() == pos.getId()) {
                   contained = true;
                   break;
               }
           }
           assertTrue(contained);
       }

   }
  
   @Test 
   public void seeWorkersOnPosition() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       PositionDAO posDao = Factory.getInstance().getPositionDAO();
       StaffMemberDAO memDao = Factory.getInstance().getStaffMemberDAO();
       EmployeeDAO empDao = Factory.getInstance().getEmployeeDAO();
       Department dep = null;
       int ind = -1;
       WebLink links[] = resp.getLinks();
       Collection<Position> poss = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            poss = posDao.getPositionsByDepartment(dep);
            for (Position p : poss) {
                Collection<StaffMember> mems = memDao.getStaffMembersByPosition(p);
                if (mems.size() != 0) {
                    ind = i;
                    break;
                }
            }
            if (ind != -1) break;
       }

       assertTrue(ind != -1);
       
       resp = wc.getResponse(links[ind].getRequest());
       links = resp.getLinks();       
       
       Collection<Department> subs = dao.getSubDepartments(dep);
       int posShift = (dep.getDirector() == null ? 0 : 1) + (dep.getHeadDepartment() == null ? 0 : 1) + subs.size();
       for (int i = 0; i < poss.size(); i++) {
           String id = links[posShift].getParameterValues("id")[0];
           Position pos = posDao.getPositionById(Long.parseLong(id));
           assertEquals(pos.getDepartment().getId(), dep.getId());
           Collection<Employee> emps = empDao.getEmployeesByPosition(pos);
           Collection<StaffMember> mems = new ArrayList<StaffMember>();
           for (Employee e : emps) {
               if (e.getEndTime() == null) {
                   mems.add(e.getStaffMember());
               }
           }
           String rightURL = "/res/staff_info";
           for (int j = 0; j < mems.size(); j++) {
               assertEquals(links[posShift + 1 + j].getURLString().substring(0, rightURL.length()), rightURL);
               String mem_id = links[posShift + 1 + j].getParameterValues("id")[0];
               boolean rightMem = false;
               for (StaffMember mem : mems) {
                   if (mem.getId().toString().equals(mem_id)) {
                       rightMem = true;
                       break;
                   }
               }
               assertTrue(rightMem);
           }
           rightURL = "/res/staff_assignment";
           for (int j = 0; j < pos.getSize() - mems.size(); j++) {
               assertEquals(links[posShift + 1 + mems.size() + j].getURLString().substring(0, rightURL.length()), rightURL);
           } 
           posShift += 1 + posDao.getPositionById(Long.parseLong(id)).getSize();
       }
   }

   @Test
   public void hireWorkerOnPosition() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       PositionDAO posDao = Factory.getInstance().getPositionDAO();
       StaffMemberDAO memDao = Factory.getInstance().getStaffMemberDAO();
       Department dep = null;
       Position pos = null;
       int ind = -1;
       WebLink links[] = resp.getLinks();
       Collection<Position> poss = null;
       Collection<StaffMember> mems = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            poss = posDao.getPositionsByDepartment(dep);
            for (Position p : poss) {
                mems = memDao.getStaffMembersByPosition(p);
                if (mems.size() < p.getSize()) {
                    pos = p;
                    ind = i;
                    break;
                }
            }
            if (ind != -1) break;
       }

       assertTrue(ind != -1);
       
       resp = wc.getResponse(links[ind].getRequest());
       //department info

       links = resp.getLinks();       
       Collection<Department> subs = dao.getSubDepartments(dep);
       int workerLink = -1;
       int posShift = (dep.getDirector() == null ? 0 : 1) + (dep.getHeadDepartment() == null ? 0 : 1) + subs.size();
       
       for (int i = 0; i < poss.size(); i++) {
           String id = links[posShift].getParameterValues("id")[0];
           if (id.equals(pos.getId().toString())) {
               posShift += mems.size() + 1;
               workerLink = posShift;
               break;
           } else { // to next position
               posShift += 1 + posDao.getPositionById(Long.parseLong(id)).getSize();
           }
       }

       assertTrue(workerLink != -1);
       String rightURL = "/res/staff_assignment";
       assertEquals(links[workerLink].getURLString().substring(0, rightURL.length()), rightURL);
       assertEquals(links[workerLink].getParameterValues("pos_id")[0], pos.getId().toString());

       resp = wc.getResponse(links[workerLink].getRequest());
       //staff assignment

       WebLink mem = resp.getLinks()[0];
       String mem_id = mem.getParameterValues("mem_id")[0];
       resp = wc.getResponse(mem.getRequest()); //member hired

       
       Collection<StaffMember> newMems = memDao.getStaffMembersByPosition(pos);
       assertEquals(newMems.size(), mems.size() + 1);
       for (StaffMember m : newMems) {
           boolean founded = false;
           for (StaffMember m1 : mems) {
               if (m.getId() == m1.getId()) {
                   founded = true;
                   break;
               }
           }
           if (!founded && m.getId().toString().equals(mem_id)) {
               founded = true;
           }
           assertTrue(founded);
       }       


  }

   @Test
   public void fireWorkerFromPosition() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       PositionDAO posDao = Factory.getInstance().getPositionDAO();
       StaffMemberDAO memDao = Factory.getInstance().getStaffMemberDAO();
       Department dep = null;
       Position pos = null;
       int ind = -1;
       WebLink links[] = resp.getLinks();
       
       Collection<Position> poss = null;
       Collection<StaffMember> mems = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            poss = posDao.getPositionsByDepartment(dep);
            for (Position p : poss) {
                mems = memDao.getStaffMembersByPosition(p);
                if (mems.size() > 0) {
                    pos = p;
                    ind = i;
                    break;
                }
            }
            if (ind != -1) break;
       }

       assertTrue(ind != -1);
       
       resp = wc.getResponse(links[ind].getRequest());

       links = resp.getLinks();       
       Collection<Department> subs = dao.getSubDepartments(dep);
       int workerLink = -1;
       int posShift = (dep.getDirector() == null ? 0 : 1) + (dep.getHeadDepartment() == null ? 0 : 1) + subs.size();
       
       WebForm forms[] = resp.getForms();
       int formShift = 0;

       for (int i = 0; i < poss.size(); i++) {
           String id = links[posShift].getParameterValues("id")[0];
           if (id.equals(pos.getId().toString())) {
               workerLink = posShift + 1;
               break;
           } else { // to next position
               posShift += 1 + posDao.getPositionById(Long.parseLong(id)).getSize();
               formShift += memDao.getStaffMembersByPosition(posDao.getPositionById(Long.parseLong(id))).size() * 2;
           }
       }

       assertTrue(workerLink != -1);
       String mem_id = links[workerLink].getParameterValues("id")[0];
       StaffMember mem = memDao.getStaffMemberById(Long.parseLong(mem_id));
       
       resp = wc.getResponse(forms[formShift + 1].getRequest(forms[formShift + 1].getSubmitButton("remove")));

       Collection<Position> newPoss = posDao.getCurrentPositionsByStaffMember(mem);
       for (Position p : newPoss) {
           assertTrue(p.getId() != pos.getId());
       }

   }

   @Test
   public void filterDepartments() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");

       WebForm form = resp.getFormWithName("filter");
       form.setParameter("filter_name", "dep");

       resp = wc.getResponse(form.getRequest(form.getSubmitButton("filter")));

       WebLink links[] = resp.getLinks();
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       Department dep = null;
       for (int i = 1; i < links.length; i++) {
            String id = links[i].getParameterValues("id")[0];
            dep = dao.getDepartmentById(Long.parseLong(id));
            assertTrue(dep.getName() != null && dep.getName().contains("dep"));
       }

       Collection<Department> deps = dao.getAllDepartments();
       int right_deps = 0;
       for (Department d : deps) {
           if (d.getName() != null && d.getName().contains("dep")) {
               right_deps++;
           }
       }
       assertEquals(right_deps, links.length - 1);

   }

   @Test
   public void addPosition() throws IOException, SAXException, SQLException {
       WebConversation wc = new WebConversation();
       WebResponse resp = wc.getResponse("http://dragon:8080/res/departments");
       WebLink links[] = resp.getLinks();

       Department dep = null;
       String dep_id = links[1].getParameterValues("id")[0];
       resp = wc.getResponse(links[1].getRequest());

       WebForm form = resp.getFormWithName("addPosition");
       resp = wc.getResponse(form.getRequest(form.getSubmitButton("add")));

       assertEquals(resp.getURL().getPath(), "/res/position_edit");
       form = resp.getFormWithName("edit");

       form.setParameter("name", "addPositionTestName");
       form.setParameter("size", "1");
       form.setParameter("duties", "addPositionDuties");

       resp = wc.getResponse(form.getRequest(form.getSubmitButton("confirm")));
       DepartmentDAO dao = Factory.getInstance().getDepartmentDAO();
       PositionDAO posDao = Factory.getInstance().getPositionDAO();

       dep = dao.getDepartmentById(Long.parseLong(dep_id));
       Collection<Position> poss = posDao.getPositionsByDepartment(dep);

       boolean exists = false;
       for (Position pos : poss) {
           if (pos.getName() != null && pos.getName().equals("addPositionTestName") 
                   && pos.getSize() == 1 && pos.getResponsibilities().equals("addPositionDuties")) {
               exists = true;
               break;
           }
       }
       assertTrue(exists);

       

   }
/*
   @Test
   public void notAddPosition() throws IOException, SAXException, SQLException {
   }

   @Test
   public void addAndDeletePosition() throws IOException, SAXException, SQLException {
   }

   @Test
   public void editPosition() throws IOException, SAXException, SQLException {

   }

   @Test
   public void notEditPosition() throws IOException, SAXException, SQLException {
   
   }

   @Test
   public void deletePosition() throws IOException, SAXException, SQLException {
   
   }

   @Test
   public void setWorkerStartTime() throws IOException, SAXException, SQLException {

   }
*/

}
