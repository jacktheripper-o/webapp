//import models.User;
//import service.DatabaseService;
//import service.SecurityService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class AdduseServlet extends HttpServlet implements Routable{
//
//
//    private SecurityService securityService;
//
//    private DatabaseService databaseService;
//
//
//    public AdduseServlet() throws SQLException, ClassNotFoundException {
//        this.databaseService = new DatabaseService();
//    }
//
//    public void refreshTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        ArrayList<String> userList = databaseService.getAllUser();
//        request.setAttribute("userList", userList);
//        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
//        rd.include(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        boolean authorized = false;
//        try {
//            authorized = securityService.isAuthorized(req);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if(authorized) {
//            String username = (String) req.getSession().getAttribute("addUser");
//            try {
//                User user = databaseService.getUser(username);
//                req.setAttribute("username", user.getUsername());
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/adduser.jsp");
//                requestDispatcher.include(req, resp);
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            } catch (ClassNotFoundException e1) {
//                e1.printStackTrace();
//            }
//        }else resp.sendRedirect("/login");
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getParameter("add_user") != null) {
//            String newUsername = req.getParameter("adding_username");
//            try {
//                if (databaseService.containUser(newUsername)) {
//                    String error = "Username exist in database";
//                    req.setAttribute("adding_error", error);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                String error = e.getMessage();
//                req.setAttribute("adding_error", error);
//            }
//            String newPassword = req.getParameter("adding_password");
//            String confirmPassword = req.getParameter("confirm_password");
//            if (newPassword.compareTo(confirmPassword) == 0) {
//                try {
//                    databaseService.createUser(newUsername, newPassword);
//                    refreshTable(req, resp);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    String error = e.getMessage();
//                    req.setAttribute("adding_error", error);
//                }
//            } else {
//                String error = "Password doesn't match";
//                req.setAttribute("adding_error", error);
//            }
//
//            if (req.getParameter("back") != null) {
//                resp.sendRedirect("/home");
//            }
//        }
//    }
//        @Override
//        public String getMapping () {
//            return "/adduser";
//        }
//
//        @Override
//        public void setSecurityService (SecurityService securityService){
//            this.securityService = securityService;
//        }
//    }
//
