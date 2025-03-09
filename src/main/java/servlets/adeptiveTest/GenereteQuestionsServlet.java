package servlets.adeptiveTest;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class GenereteQuestionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Generete Questions Servlet");
        out.close();
    }
}
