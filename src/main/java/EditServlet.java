import models.User;
import service.DatabaseService;
import service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet implements Routable{


    private SecurityService securityService;

    private DatabaseService databaseService;


    public EditServlet() throws SQLException, ClassNotFoundException {
        this.databaseService = new DatabaseService();
    }

    @Override
    public String getMapping() {
        return "/edit";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean authorized = false;
        try {
            authorized = securityService.isAuthorized(req);
        } catch(SQLException ex) {
        }
        if(authorized){
            String username = (String)req.getSession().getAttribute("editing_user");
            try {
                User user = databaseService.getUser(username);
                req.setAttribute("username", user.getUsername());
                req.setAttribute("first_name", user.getFirstName());
                req.setAttribute("last_name", user.getLastName());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(req,resp);
        } else resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("editing_user");
        if (req.getParameter("edit_user") != null) {

            try {
                String newUsername = req.getParameter("newUsername");
                if (databaseService.containUser(newUsername)) {
                    req.setAttribute("error", "User already exist");
                } else {
                    databaseService.updateUsername(newUsername, username);
                    username = newUsername;
                }
                req.getSession().setAttribute("editing_user", username);

                String newPassword = req.getParameter("password");
                String confirmPassword = req.getParameter("confirmPassword");
                if (newPassword.equals(confirmPassword)) {
                    String error = "Password doesn't match";
                    req.setAttribute("password_error", error);
                } else {
                    databaseService.updatePassword(newPassword, username);

                }

                String newFirstname = req.getParameter("firstName");

                databaseService.updateFirstName(newFirstname, username);


                String newLastName = req.getParameter("lastName");

                databaseService.updateLastName(newLastName, username);


                User user = databaseService.getUser(username);
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                req.setAttribute("username", username);
                req.setAttribute("firstName", firstName);
                req.setAttribute("lastName", lastName);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(req, resp);
        }
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
