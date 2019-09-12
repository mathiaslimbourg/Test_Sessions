package be.intecbrussel.webcomponents;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);

        HttpSession session = req.getSession();
        session.invalidate();
        out.println("Logged out!");

        out.close();
    }


}
