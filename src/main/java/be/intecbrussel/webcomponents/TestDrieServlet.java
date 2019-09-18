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
        //opletten, wanneer je de pagina opnieuw bezoekt dan zal je toch naar de welcome page worden geleid, ookal ben je niet ingelogd.
        //req.getSession() maakt een session, deze zal blijven bestaan tot ze timed out is of tot je ze zelf invalidate
        //denk aan het gebruik van eventuele extra attributes?
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
        //wat als de naam "   " is?
        if (name.equals("")){
            // 1 herhaalde code, zie 2
            HttpSession session = req.getSession();
            //de message wordt geset, wanneer je de pagina opnieuw bezoekt blijft ze echter gewoon staan
            session.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);


        } else {
            //2 herhaalde code, zie 1
            HttpSession session = req.getSession();
            session.setAttribute("name",name);
            session.setAttribute("message","");
            req.getRequestDispatcher("/WEB-INF/welcome.jsp").include(req,resp);

        }
    }
}
