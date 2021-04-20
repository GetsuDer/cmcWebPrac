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
   public String departmentEdit(ModelMap model) {
       return "department_edit";
   }
   
   @RequestMapping(value="/department_info", method = RequestMethod.GET)
   public String departmentInfo(@RequestParam(name="id", required=true) String id, ModelMap model) throws SQLException {
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        Department department = departmentDAO.getDepartmentById(Long.parseLong(id));
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

   @RequestMapping(value="/staff", method = RequestMethod.GET)
   public String printStaff(ModelMap model) {
       return "staff";
   }

   @RequestMapping(value="/staff_edit", method = RequestMethod.GET)
   public String staffEdit(ModelMap model) {
       return "staff_edit";
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
