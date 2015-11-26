package websearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lucene.Result;
import lucene.SearchEngine;

import org.apache.lucene.queryparser.classic.ParseException;

/**
 * Servlet implementation class WebResult
 */
public class WebResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebResult() {
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
		
		out.println("<body>");
    	String docId = request.getParameter("d");
        
		SearchEngine se = new SearchEngine();
		String temp = se.getDocString(Integer.valueOf(docId));
		out.println(temp);

		out.println("<br><button onclick=goBack()>Go Back</button>");
		out.println("<script>function goBack() {window.history.back();}</script>");
	   		
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
