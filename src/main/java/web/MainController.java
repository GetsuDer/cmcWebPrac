package web;

import logic.*;
import DAO.*;
import DAO.Factory;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
   public String gotoDepartmentEdit(ModelMap model) throws SQLException {
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
   
   @RequestMapping(value="/staff", method = RequestMethod.GET)
   public String printStaff(ModelMap model) {
       return "staff";
   }
}
