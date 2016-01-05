package websearch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebIndex
 */
public class WebIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>- Phra A Pai Ma Nee - 5610400091</title>");
	    out.println("<b> Search Engine - </b>");
	    out.println("</head>");
	    
	    out.println("<body>");
	    
	    out.println("<form action=\"http://localhost:8080/IRProject/result\" method=\"GET\"><br>");
	    out.println("Enter your query: <input type=\"text\" name=\"query\" size=40><p></p>");
	    
	    out.println("<input type=\"submit\" value=\"Submit Query\">");
	    out.println("<input type=\"reset\" value=\"Reset Form\"> </form>");

	    out.println("</body>");
	    out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
