

import models.User;
import service.DatabaseService;
import service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class HomeServlet extends HttpServlet implements Routable{

    private SecurityService securityService;

    private DatabaseService databaseService;

    public HomeServlet() throws SQLException, ClassNotFoundException {
        this.databaseService = new DatabaseService();
    }

    public void refreshTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        ArrayList<String> userList = databaseService.getAllUser();
        request.setAttribute("userList", userList);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
        rd.include(request, response);
    }

    @Override
    public String getMapping() {
        return "/index.jsp";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = false;
        try {
            authorized = securityService.isAuthorized(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (authorized) {
            // do MVC in here
            String username = (String) request.getSession().getAttribute("username");
            try {
                User user = databaseService.getUser(username);
                request.getSession().setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                refreshTable(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("removing_user")!=null){
            String user = req.getParameter("user_to_use");
            try {
                databaseService.delUser(user);
                refreshTable(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                String error = e.getMessage();
                req.setAttribute("removing_error", error);
            }
        }else if(req.getParameter("do_edit")!=null){
            String user = req.getParameter("user_to_use");
            req.getSession().setAttribute("editing_user", user);
            resp.sendRedirect("/edit");
        }else if(req.getParameter("logout")!= null){
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        }
//        else if(req.getParameter("addUser")!= null){
//            req.getSession().invalidate();
//            resp.sendRedirect("/adduser");
//        }
    }

}
