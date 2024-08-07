package controller;

import dbconnext.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.AccountManager;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Account account;
//---------------------------------------------------------------------------------------------------
        AccountManager am = new AccountManager();
        if (am.Login(username, password) == null) {
            session.setAttribute("Error", "Wrong username or password");
            response.sendRedirect("LoginServlet");
        } else {
            account = am.Login(username, password);
            if (!account.isTrangthaixacthuc()) { // Kiểm tra trạng thái xác thực
                request.setAttribute("errorMessage", "Account is not verified. Please verify your account.");
                request.setAttribute("Account", account);
                request.getRequestDispatcher("VerifyPage").forward(request, response);
                return;
            }
            if (remember != null) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
            }
            session.setAttribute("account", account);
            if (account.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect("AdminPage");
            } else if (account.getRole().equalsIgnoreCase("DinerManager")) {
                response.sendRedirect("DinerPage");
            } else if (account.getRole().equalsIgnoreCase("Shipper")) {
                response.sendRedirect("ShipperPage");
            } else {
                response.sendRedirect("index.jsp");
            }
        } 
//------------------------------------------------------------------------------------------------------------
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
