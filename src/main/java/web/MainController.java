package web;

import logic.*;
import DAO.*;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Collection;

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
       model.addAttribute("headDepartment", "");
       return "redirect:department_edit";
   }
   
   @RequestMapping(value="/department_edit", method = RequestMethod.GET)
   public String departmentEdit(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
       Department dep = Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(id));
       model.addAttribute("id", id);
       model.addAttribute("name", dep.getName());
       model.addAttribute("director", dep.getDirector().getId());
       model.addAttribute("headDepartment", dep.getHeadDepartment().getId());
       return "department_edit";
   }
   
   @RequestMapping(value="/confirm_department", method = RequestMethod.GET)
   public String changeDepartment(@RequestParam(name="id") String id, @RequestParam(name="name") String name, @RequestParam(name="director") String director, @RequestParam(name="headDepartment") String headDepartment, ModelMap model) throws SQLException {
       DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
       StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
       Department dep = departmentDAO.getDepartmentById(Long.parseLong(id));
       Department head = departmentDAO.getDepartmentById(Long.parseLong(headDepartment));
       StaffMember mem = memberDAO.getStaffMemberById(Long.parseLong(director));
       dep.setName(name);
       dep.setDirector(mem);
       dep.setHeadDepartment(head);
       departmentDAO.updateDepartment(dep);
       return "departments";   
   }

   @RequestMapping(value="/department_info", method = RequestMethod.GET)
   public String departmentInfo(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        Department department = departmentDAO.getDepartmentById(Long.parseLong(id));
        model.addAttribute("id", id);
        model.addAttribute("name", department.getName());
        model.addAttribute("director", department.getDirector().getId());
        model.addAttribute("headDepartment", department.getHeadDepartment().getId());
        Collection<Department> subDeps = departmentDAO.getSubDepartments(department);
        for (Department dep : subDeps) {
            System.out.println(dep);
        }
        Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByDepartment(department);
        for (Position pos : positions) {
            System.out.println(pos);
        }

       return "department_info";   
   }

   @RequestMapping(value="/delete_department", method = RequestMethod.GET)
   public String deleteDepartment(@RequestParam(name="id") String id, ModelMap model) throws SQLException {
       DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
       Department dep = departmentDAO.getDepartmentById(Long.parseLong(id));
       departmentDAO.deleteDepartment(dep);
       return "departments";
   }

   @RequestMapping(value="/staff", method = RequestMethod.GET)
   public String printStaff(ModelMap model) {
       return "staff";
   }

   @RequestMapping(value="/staff_edit", method = RequestMethod.GET)
   public String staffEdit(ModelMap model) {
       return "staff_edit";
   }
   @RequestMapping(value="/staff_info", method = RequestMethod.GET)
   public String staffInfo(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
       StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
       StaffMember mem = staffMemberDAO.getStaffMemberById(Long.parseLong(id));
       model.addAttribute("name", mem.getName());
       model.addAttribute("address", mem.getAddress());
       model.addAttribute("education", mem.getEducation());
       model.addAttribute("workStart", mem.getWorkStart().toString());
       Collection<Position> positions = Factory.getInstance().getPositionDAO().getAllPositionsByStaffMember(mem); 
       for (Position pos : positions) {
           System.out.println(pos);
       }
       return "staff_info";
   }

   @RequestMapping(value="/add_staff", method = RequestMethod.GET)
   public String gotoStaffEdit(ModelMap model) {
       model.addAttribute("id", "-1");
       model.addAttribute("name", "");
       model.addAttribute("education", "");
       model.addAttribute("address", "");
       model.addAttribute("employment_date", "");
       return "redirect:staff_edit";
   }
}
