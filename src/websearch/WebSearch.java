package websearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;

import lucene.Result;
import lucene.SearchEngine;

/**
 * Servlet implementation class SimpleSearch
 */
public class WebSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebSearch() {
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
    	String query = request.getParameter("query");
    	query = (query == null)?null:new String(query.getBytes("iso-8859-1"), "UTF-8");
        
        try {
			SearchEngine se = new SearchEngine();
			Result[] temp = se.searchPerformed(query);
			out.println(temp[0].getName());
			for(int i=1; i<temp.length; i++){
				Result r = temp[i];
				out.println("<br>");
				out.println(r.getOrder() + ": ");
				out.println("<a href=\"http://localhost:8080/IRProject/doc?d=" + r.getId() + "\">" + r.getName() + "</a>");
				out.println("(" + r.getScore() + ")");
				out.println("<br>");
			}
		} catch (ParseException e) {
			e.getStackTrace();
		}

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
