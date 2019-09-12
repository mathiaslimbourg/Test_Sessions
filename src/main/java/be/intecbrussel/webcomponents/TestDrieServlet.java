package be.intecbrussel.webcomponents;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class TestDrieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().isNew()){
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String message = "Gelieve een geldige naam in te voeren";

        if (name.equals("")){
            HttpSession session = req.getSession();

            session.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);


        } else {

            HttpSession session = req.getSession();
            session.setAttribute("name",name);
            session.setAttribute("message","");

            req.getRequestDispatcher("/WEB-INF/welcome.jsp").include(req,resp);

        }
    }
}
