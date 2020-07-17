

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

public class AddUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    private DatabaseService databaseService;

    public AddUserServlet() throws SQLException, ClassNotFoundException {
        this.databaseService = new DatabaseService();
    }

    public void refreshTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        ArrayList<String> userList = databaseService.getAllUser();
        request.setAttribute("userList", userList);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
        rd.include(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (securityService.isAuthorized(request)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adduser.jsp");
                requestDispatcher.include(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getParameter("add_user") != null) {
        String newUsername = request.getParameter("adding_username");
        try {
            if (databaseService.containUser(newUsername)) {
                String error = "Username exist in database";
                request.setAttribute("adding_error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/adduser.jsp");
                rd.include(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String error = e.getMessage();
            request.setAttribute("adding_error", error);
        }
        String newPassword = request.getParameter("adding_password");
        String confirmPassword = request.getParameter("confirm_password");
        if (newPassword.equals(confirmPassword)) {
            try {
                databaseService.createUser(newUsername, newPassword);
                refreshTable(request, response);
                response.sendRedirect("/");
            } catch (SQLException e) {
                e.printStackTrace();
                String error = e.getMessage();
                request.setAttribute("adding_error", error);
            }
        } else {
            String error = "Password doesn't match";
            request.setAttribute("adding_error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/adduser.jsp");
            rd.include(request, response);
        }
    }
}



    @Override
    public String getMapping() {
        return "/adduser";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}

