/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.23
 * Generated at: 2017-12-05 07:07:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1508096058793L));
    _jspx_dependants.put("jar:file:/C:/Users/Kevin/Desktop/RoadReportSpring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/RoadReport/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153410282000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\t\n");
      out.write("    <!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("    <html>\n");
      out.write("        <style>\n");
      out.write("              html *\n");
      out.write("        {\n");
      out.write("   font-size: 1em;\n");
      out.write("   font-family: \"Arial Black\";\n");
      out.write("}\n");
      out.write("body {background-color: white;}\n");
      out.write("h1 {color: white; text-align: center}\n");
      out.write("p {color: white;}\n");
      out.write("a {text-decoration: none}\n");
      out.write(".header{ \n");
      out.write("    background-color: #f49542;\n");
      out.write("    color: black;\n");
      out.write("    text-align: center;\n");
      out.write("    padding: 14px 16px;\n");
      out.write("    text-decoration: none;\n");
      out.write("    }\n");
      out.write(".topnav {\n");
      out.write("    overflow: hidden;\n");
      out.write("    background-color: #bcbcbc;\n");
      out.write("}    \n");
      out.write(".topnav a {\n");
      out.write("    float: left;\n");
      out.write("    display: block;\n");
      out.write("    color: black;\n");
      out.write("    text-align: center;\n");
      out.write("    padding: 14px 16px;\n");
      out.write("    text-decoration: none;\n");
      out.write("    }\n");
      out.write(".topnav a:hover {\n");
      out.write("    background-color: #ddd;\n");
      out.write("    color: black;\n");
      out.write("    }\n");
      out.write(".imgFlip {\n");
      out.write("        -moz-transform: scaleX(-1);\n");
      out.write("        -o-transform: scaleX(-1);\n");
      out.write("        -webkit-transform: scaleX(-1);\n");
      out.write("        transform: scaleX(-1);\n");
      out.write("        filter: FlipH;\n");
      out.write("        -ms-filter: \"FlipH\";\n");
      out.write("\t}\n");
      out.write("\t /* Remove margins from the 'html' and 'body' tags, and ensure the page takes up full screen height. */\n");
      out.write("            html, body {height:100%; margin:0; padding:0;}\n");
      out.write("\n");
      out.write("            /* Set the position and dimensions of the background image. */\n");
      out.write("            #page-background {position:fixed; top:0; left:0; width:100%; height:100%;}\n");
      out.write("\n");
      out.write("            /* Specify the position and layering for the content that needs to appear in front of the background image. Must have a higher z-index value than the background image. Also add some padding to compensate for removing the margin from the 'html' and 'body' tags. */\n");
      out.write("            #content {position:relative; z-index:1; padding:10px;}\n");
      out.write("}\n");
      out.write("\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("    <title>Road Report</title>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write(" <div id=\"page-background\"><img src=\"lights.png\" width=\"100%\" height=\"100%\" alt=\"bg\"></div>\n");
      out.write("        <div id=\"content\">\n");
      out.write("\n");
      out.write("    <div class=\"header\" style=\"border-radius: 10px 10px 0px 0px\">\n");
      out.write("    <img src=\"cone.gif\" alt=\"cone\" class=\"imgFlip\"style=\"width:20px;height:25px\">\n");
      out.write("        ROAD REPORT\n");
      out.write("        <img src=\"cone.gif\" alt=\"cone\"style=\"width:20px;height:25px\">\n");
      out.write("    </div>\n");
      out.write("    </a>\n");
      out.write("        <div class=\"topnav\" style=\"border-radius: 0px 0px 10px 10px\">\n");
      out.write("\t\t<a href=map>Map</a>\n");
      out.write("        <a href=reportHazard>Report Hazard</a>\n");
      out.write("        \t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t<a href=home.jsp style=\"float: right;\">Logout</a>\n");
      out.write("    </div>\n");
      out.write("        <table>\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("\n");
      out.write("                <td  style=\"color: white;\">Welcome ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${firstname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(", choose an option in the navigation bar above to get started!</td>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("           \n");
      out.write("        </table>\n");
      out.write("    </div>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("    </html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /jsp/welcome.jsp(78,9) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session eq 'Resolver' }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("    \t\t<a href=\"reportConstructionHazard\">Report Construction Hazard</a>\n");
          out.write("    \t\t<a href=\"resolveHazard\">Resolve Hazard</a>\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }
}