package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import logic.*;
import DAO.*;
import java.util.Collection;
import java.util.Iterator;

public final class departments_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Department page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <p> <h1> <center> <a href=\"/res/\">Main</a> </p>\n");
      out.write("        <p> \n");
      out.write("        <form method=\"get\" action=\"/res/add_department\">\n");
      out.write("            <input type=\"submit\" value=\"Add department\">\n");
      out.write("        </form>\n");
      out.write("        </p>\n");
      out.write("        <form method=\"get\" action=\"/find\">\n");
      out.write("            Name:<input type=\"text\" name=\"name\">\n");
      out.write("            <input type=\"submit\" value=\"Filter\">\n");
      out.write("        </form>\n");
      out.write("        </h1>\n");
      out.write("        ");

            Collection departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            Iterator iterator = departments.iterator();
            while (iterator.hasNext()) {
                Department department = (Department) iterator.next();
                out.println("<br>" + department + "<a href=/res/department_info?id=" + department.getId() + ">edit</a>");
                
            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
