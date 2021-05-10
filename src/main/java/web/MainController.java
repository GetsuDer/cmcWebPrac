package web;

import logic.*;
import DAO.*;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
public class MainController {
   @RequestMapping(value="/", method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      return "index";
   }
   
   @RequestMapping(value="/departments", method = RequestMethod.GET)
   public String printDepartments(ModelMap model) {
       return "departments";
   }

   @RequestMapping(value="/add_department", method = RequestMethod.GET)
   public String gotoDepartmentEdit(ModelMap model) {
       model.addAttribute("id", "-1");
       model.addAttribute("name", "");
       model.addAttribute("director", "");
       model.addAttribute("director_id", "");
       model.addAttribute("head_id", "");
       return "redirect:department_edit";
   }
   
   @RequestMapping(value="/department_edit", method = RequestMethod.GET)
   public String departmentEdit(@RequestParam(name="id") String id, @RequestParam(name="director_id") String director_id, @RequestParam(name="head_id", required=false) String head_id, ModelMap model) throws SQLException {
       if (Long.parseLong(id) != -1) {
           Department dep = Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(id));
           model.addAttribute("id", id);
           if (head_id == null) {
               Department head_dep = dep.getHeadDepartment();
               if (head_dep == null) {
                   head_id = "-1";   
               } else {
                   head_id = head_dep.getId().toString();
               }
           }
           if (head_id.equals("-1")) {
               model.addAttribute("head", "");
           } else {
               model.addAttribute("head", Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(head_id)).getName());
           }
           model.addAttribute("name", (dep.getName() == null) ? "" : dep.getName());
           model.addAttribute("head_id", head_id);
           
           if (director_id.equals("-1")) {
               model.addAttribute("director", "");
               model.addAttribute("director_id", "-1");
           } else {
               StaffMember director = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(director_id));
               model.addAttribute("director_id", director.getId());
               model.addAttribute("director", director.getName());
           }
           model.addAttribute("headDepartment", (dep.getHeadDepartment() == null) ? "" : dep.getHeadDepartment().getId());
       } else {
           model.addAttribute("id", "-1");
           model.addAttribute("name", "");
           model.addAttribute("director", "");
           model.addAttribute("head_id", "-1");
       }
       return "department_edit";
   }
   
   @RequestMapping(value="/deleteOldEmployee", method = RequestMethod.GET)
   public String deleteOldEmployee(@RequestParam(name="id") String id, @RequestParam(name="dep_id") String dep_id, @RequestParam(name="director_id") String director_id, @RequestParam(name="back") String back, ModelMap model) throws SQLException {
       EmployeeDAO dao = Factory.getInstance().getEmployeeDAO();
       Employee emp = dao.getEmployeeById(Long.parseLong(id));
       dao.deleteEmployee(emp);
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("id", id);
       model.addAttribute("back", back);
       model.addAttribute("director_id", director_id);
       return "redirect:staff_info";
   }

   @RequestMapping(value="/editOldEmployee", method = RequestMethod.GET)
   public String editOldEmployee(@RequestParam(name="id") String id, @RequestParam(name="workStart") String workStart, @RequestParam(name="dep_id") String dep_id, @RequestParam(name="director_id") String director_id, @RequestParam(name="back") String back, @RequestParam(name="workEnd") String workEnd, ModelMap model) throws SQLException {
       EmployeeDAO dao = Factory.getInstance().getEmployeeDAO();
       Employee emp = dao.getEmployeeById(Long.parseLong(id));
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       if (!workStart.equals("")) {
           try {
               emp.setStartTime(format.parse(workStart));
           } catch (ParseException e) {}
       }
       if (!workEnd.equals("")) {
           try {
               emp.setEndTime(format.parse(workEnd));
           } catch (ParseException e) {}
       }
       dao.updateEmployee(emp);
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("director_id", director_id);
       model.addAttribute("id", id);
       model.addAttribute("back", back);
       return "redirect:staff_info";
   }

   @RequestMapping(value="/staff_assignment", method = RequestMethod.GET)
   public String staffAssignment(@RequestParam(name="dep_id") String dep_id, @RequestParam(name="head_id", required=false) String head_id, @RequestParam(name="director_id", required=false) String director_id, @RequestParam(name="position") String position, @RequestParam(name="pos_id", required=false) String pos_id, ModelMap model) {
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("position", position);
       model.addAttribute("head_id", head_id == null ? "-1" : head_id);
       model.addAttribute("director_id", director_id == null ? "-1" : director_id);
       if (pos_id != null) model.addAttribute("pos_id", pos_id);
       return "staff_assignment";
   }
   
   @RequestMapping(value="/remove_worker", method = RequestMethod.GET)
   public String removeWorker(@RequestParam(name="pos_id") String pos_id, @RequestParam(name="mem_id") String mem_id, @RequestParam(name="dep_id") String dep_id,  @RequestParam(name="workEnd") String workEnd, ModelMap model) throws SQLException {
        Position pos = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(pos_id));
        EmployeeDAO dao = Factory.getInstance().getEmployeeDAO();
        Collection <Employee> emps = dao.getEmployeesByPosition(pos);
        model.addAttribute("id", dep_id);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
        for (Employee emp : emps) {
            if (emp.getEndTime() == null && emp.getStaffMember().getId().toString().equals(mem_id)) {
                try {
                    emp.setEndTime(format.parse(workEnd));
                } catch (ParseException e) {

                }
                dao.updateEmployee(emp);
                break;
            }
        }
        return "redirect:department_info";

   }

   @RequestMapping(value="/setWorkStart", method = RequestMethod.GET)
   public String setWorkStart(@RequestParam(name="pos_id") String pos_id, @RequestParam(name="mem_id") String mem_id, @RequestParam(name="workStart") String workStart, ModelMap model) throws SQLException {
       Position pos = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(pos_id));
       StaffMember mem = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(mem_id));

       model.addAttribute("id", pos.getDepartment().getId());
       EmployeeDAO dao = Factory.getInstance().getEmployeeDAO();
       Collection<Employee> emps = dao.getEmployeesByPosition(pos);
       for (Employee emp : emps) {
           if (emp.getEndTime() == null && emp.getStaffMember().getId() == mem.getId()) {
               SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
               try {
                   emp.setStartTime(format.parse(workStart));
               } catch (ParseException e) {
               }
               dao.updateEmployee(emp);
               break;
           }
       }
       return "redirect:department_info";
   }
   @RequestMapping(value="/department_assignment", method = RequestMethod.GET)
   public String departmentAssignment(@RequestParam(name="dep_id") String dep_id, @RequestParam(name="head_id") String head_id, @RequestParam(name="director_id") String director_id, ModelMap model) {
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("head_id", head_id);
       model.addAttribute("director_id", director_id);
       return "department_assignment";
   }

   @RequestMapping(value="/filter_departments", method = RequestMethod.GET)
   public String filterDepartments(@RequestParam(name="filter_name") String filter_name, ModelMap model) {
        model.addAttribute("filter_name", filter_name);
        return "departments";
   }

   @RequestMapping(value="/filter_staff", method = RequestMethod.GET)
   public String filterStaff(@RequestParam(name="filter_name") String filter_name, @RequestParam(name="filter_address") String filter_address, @RequestParam(name="filter_workStart") String filter_workStart, ModelMap model) {
       model.addAttribute("filter_name", filter_name);
       model.addAttribute("filter_address", filter_address);
       model.addAttribute("filter_workStart", filter_workStart);
       return "staff";
   }

   @RequestMapping(value="/confirm_department", method = RequestMethod.GET)
   public String changeDepartment(@RequestParam(name="id") String id, @RequestParam(name="name") String name, @RequestParam(name="director_id", required=false) String director_id, @RequestParam(name="head_id") String head_id, ModelMap model) throws SQLException {
       DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
       StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
       Department dep = new Department();
       if (Long.parseLong(id) != -1) {
           dep = departmentDAO.getDepartmentById(Long.parseLong(id));
       }
       if (name.equals("")) name = null;
       if (head_id.equals("")) head_id = "-1";
       if (director_id == null) director_id = "-1";
       if (director_id.equals("")) director_id = "-1";

       Department head = (head_id.equals("-1")) ? null : departmentDAO.getDepartmentById(Long.parseLong(head_id));
       
       StaffMember mem = (director_id.equals("-1")) ? null : memberDAO.getStaffMemberById(Long.parseLong(director_id));
       
       dep.setName(name);
       dep.setDirector(mem);
       dep.setHeadDepartment(head);

       if (Long.parseLong(id) == -1) {
           departmentDAO.addDepartment(dep);
       } else {
           departmentDAO.updateDepartment(dep);
       }
       return "redirect:departments";   
   }

   @RequestMapping(value="/department_info", method = RequestMethod.GET)
   public String departmentInfo(@RequestParam(name="id") String id, @RequestParam(name="director_id", required=false) String director_id, @RequestParam(name="pos_id", required=false) String pos_id, @RequestParam(name="mem_id", required=false) String mem_id,  ModelMap model) throws SQLException {
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        Department department = departmentDAO.getDepartmentById(Long.parseLong(id));
        
        if (pos_id != null) {
            Position pos = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(pos_id));
            StaffMember mem = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(mem_id));
            EmployeeDAO dao = Factory.getInstance().getEmployeeDAO();
            Employee emp = new Employee(pos, mem, null, null);
            dao.addEmployee(emp);
        }
        
        model.addAttribute("id", id);
        model.addAttribute("name", (department.getName() == null) ? "" : department.getName());
        model.addAttribute("head_id", (department.getHeadDepartment() == null) ? "-1" : department.getHeadDepartment().getId().toString());
        model.addAttribute("head", (department.getHeadDepartment() == null) ? "" : department.getHeadDepartment().getName());
        if (director_id != null && director_id.equals("-1")) {
            model.addAttribute("director", "");
            model.addAttribute("director_id", "-1");
        } else {
            StaffMember dir = new StaffMember();
            if (director_id == null) {
                dir = department.getDirector();
                if (dir == null) {
                    model.addAttribute("director", "");
                    model.addAttribute("director_id", "-1");
                } else {
                    director_id = dir.getId().toString();
                    model.addAttribute("director", dir.getName());
                    model.addAttribute("director_id", director_id);
                }
            } else {
                dir = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(director_id));
            
                model.addAttribute("director", dir.getName());
                model.addAttribute("director_id", director_id);
            }
        }
        model.addAttribute("headDepartment", (department.getHeadDepartment() == null) ? "" : department.getHeadDepartment().getId());
        Collection<Department> subDeps = departmentDAO.getSubDepartments(department);
        ArrayList<String> subs = new ArrayList<String>();
        for (Department dep : subDeps) {
            subs.add(dep.toString());
        }
        model.addAttribute("subs", subs);
        Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByDepartment(department);
        ArrayList<String> poss = new ArrayList<String>();
        for (Position pos : positions) {
            poss.add(pos.toString());
        }
        model.addAttribute("poss", poss);

       return "department_info";   
   }

   @RequestMapping(value="/delete_department", method = RequestMethod.GET)
   public String deleteDepartment(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
       DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
       Department dep = departmentDAO.getDepartmentById(Long.parseLong(id));
       departmentDAO.deleteDepartment(dep);
       return "departments";
   }

   @RequestMapping(value="/position_edit", method = RequestMethod.GET)
   public String editPosition(@RequestParam(name="id") String id, @RequestParam(name="dep_id") String dep_id, ModelMap model) throws SQLException {
       PositionDAO dao = Factory.getInstance().getPositionDAO();
       Position pos = new Position();
       if (!id.equals("-1")) {
            pos = dao.getPositionById(Long.parseLong(id));
       }
       model.addAttribute("name", (pos.getName() == null) ? "" : pos.getName());
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("id", id);
       model.addAttribute("size", (pos.getSize() == null) ? "" : pos.getSize());
       model.addAttribute("duties", (pos.getResponsibilities() == null) ? "" : pos.getResponsibilities());
       return "position_edit";
   }

   @RequestMapping(value="/delete_position", method = RequestMethod.GET)
   public String deletePosition(@RequestParam(name="id") String id, @RequestParam(name="dep_id") String dep_id, ModelMap model) throws SQLException {
       if (id.equals("-1")) {
           model.addAttribute("id", dep_id);
           return "redirect:department_info";
       }
       PositionDAO dao = Factory.getInstance().getPositionDAO();
       Position pos = dao.getPositionById(Long.parseLong(id));
       model.addAttribute("id", dep_id);
       dao.deletePosition(pos);
       return "redirect:department_info";
   }

   @RequestMapping(value="/confirm_position", method = RequestMethod.GET)
   public String confirmPosition(@RequestParam(name="id") String id, @RequestParam(name="dep_id") String dep_id, @RequestParam(name="name") String name, @RequestParam(name="size") String size, @RequestParam(name="duties") String duties, ModelMap model) throws SQLException {
       PositionDAO dao = Factory.getInstance().getPositionDAO();
       Position pos = new Position();
       if (!id.equals("-1")) {
           pos = dao.getPositionById(Long.parseLong(id));
       }
       if (name.equals("")) name = null;
       if (size.equals("")) size = "0";
       if (duties.equals("")) duties = null;
       pos.setName(name);
       Collection<StaffMember> workers = Factory.getInstance().getStaffMemberDAO().getStaffMembersByPosition(pos);
       Long new_size = Long.parseLong(size);
       if (new_size >= workers.size()) pos.setSize(new_size);
       pos.setResponsibilities(duties);
       if (pos.getDepartment() == null) {
           pos.setDepartment(Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(dep_id)));
       }
       if (id.equals("-1")) {
           dao.addPosition(pos);
       } else {
           dao.updatePosition(pos);
       }
       model.addAttribute("id", dep_id);
       return "redirect:department_info";
   }

   @RequestMapping(value="/staff", method = RequestMethod.GET)
   public String printStaff(ModelMap model) {
       model.addAttribute("filter_name", "");
       model.addAttribute("filter_address", "");
       model.addAttribute("filter_workStart", "");
       return "staff";
   }
    
   @RequestMapping(value="/delete_staff", method = RequestMethod.GET)
   public String deleteStaff(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
       StaffMemberDAO dao = Factory.getInstance().getStaffMemberDAO();
       StaffMember mem = dao.getStaffMemberById(Long.parseLong(id));
       dao.deleteStaffMember(mem);
       return "staff";
   }

   @RequestMapping(value="/add_staff", method = RequestMethod.GET)
   public String gotoStaffEdit(ModelMap model) {
       model.addAttribute("id", "-1");
       model.addAttribute("name", "");
       model.addAttribute("address", "");
       model.addAttribute("education", "");
       model.addAttribute("workStart", "");
       return "redirect:staff_edit";
   }

   @RequestMapping(value="/confirm_staff", method=RequestMethod.GET)
   public String confirmStaff(@RequestParam(name="id") String id, @RequestParam(name="name") String name, @RequestParam(name="address") String address, @RequestParam(name="education") String education, @RequestParam(name="workStart") String workStart, ModelMap model) throws SQLException {
       StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
       StaffMember mem = new StaffMember();
       if (Long.parseLong(id) != -1) {
           mem = memberDAO.getStaffMemberById(Long.parseLong(id));
       }
       if (address.equals("")) address = null;
       if (education.equals("")) education = null;
       if (name.equals("")) name = null;
       if (workStart.equals("")) workStart = null;
       mem.setName(name);
       mem.setAddress(address);
       mem.setEducation(education);
       DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       if (workStart == null) {
           mem.setWorkStart(null);
       } else {
           try {
               mem.setWorkStart(format.parse(workStart));
           } catch (ParseException e) {
           }
       }
       if (Long.parseLong(id) == -1) {
           memberDAO.addStaffMember(mem);
       } else {
           memberDAO.updateStaffMember(mem);
       }
       return "redirect:staff";
   }

   @RequestMapping(value="/staff_edit", method = RequestMethod.GET)
   public String staffEdit(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
       StaffMember mem = new StaffMember();
       model.addAttribute("id", id);
       if (Long.parseLong(id) == -1) {
            model.addAttribute("name", "");
            model.addAttribute("address", "");
            model.addAttribute("education", "");
            model.addAttribute("workStart", "");
       } else {
            mem = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(id));
            model.addAttribute("name", (mem.getName() == null) ? "" : mem.getName());
            model.addAttribute("address", (mem.getAddress() == null) ? "" : mem.getAddress());
            model.addAttribute("education", (mem.getEducation() == null) ? "" : mem.getEducation());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("workStart", (mem.getWorkStart() == null) ? "" : format.format(mem.getWorkStart()));
       }
       return "staff_edit";
   }
   @RequestMapping(value="/staff_info", method = RequestMethod.GET)
   public String staffInfo(@RequestParam(name="id", required=true) String id, @RequestParam(name="director_id", required=false) String director_id, @RequestParam(name="dep_id", required=false) String dep_id,  @RequestParam(name="back") String back, ModelMap model) throws SQLException {
       StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
       StaffMember mem = staffMemberDAO.getStaffMemberById(Long.parseLong(id));
       model.addAttribute("id", mem.getId());
       model.addAttribute("back", back);
       model.addAttribute("director_id", director_id);
       model.addAttribute("dep_id", dep_id);
       model.addAttribute("name", (mem.getName() == null) ? "" : mem.getName());
       model.addAttribute("address", (mem.getAddress() == null) ? "" : mem.getAddress());
       model.addAttribute("education", (mem.getEducation() == null) ? "" : mem.getEducation());
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       model.addAttribute("workStart", (mem.getWorkStart() == null) ? "" : format.format(mem.getWorkStart()));
       return "staff_info";
   }

}
