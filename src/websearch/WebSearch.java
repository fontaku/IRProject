package websearch;

import java.io.IOException;
import java.io.PrintWriter;

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
	
	private static final int DOC_PER_PAGE = 5;
       
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
    	String pageStr = request.getParameter("page");
    	if(pageStr == null){
    		pageStr = "1";
    	}
    	int page = Integer.parseInt(pageStr);
    	
    	query = (query == null)?null:new String(query.getBytes("iso-8859-1"), "UTF-8");
        
        try {
			SearchEngine se = new SearchEngine();
			se.searchPerformed(query);
			Result[] temp = se.getResult((page-1)*DOC_PER_PAGE+1, DOC_PER_PAGE);
			out.println(temp[0].getName());
			for(int i=1; i<temp.length; i++){
				Result r = temp[i];
				out.println("<br>");
				out.println(r.getOrder() + ": ");
				out.println("<a href=\"http://localhost:8080/IRProject/doc?d=" + r.getId() + "\">" + r.getName() + "</a>");
				out.println("(" + r.getScore() + ")");
				out.println("<br>");
				out.println(". . . " + r.getBefore() + "<span style=\"background-color: #FFFF00\">" + r.getExact() + "</span>" + r.getAfter() + " . . .");
				out.println("<br>");
			}
			
			int totalDoc = se.getTotalResult();
			out.println("<br> PAGE << ");
			for(int i=1; i<Math.ceil(totalDoc/DOC_PER_PAGE)+2; i++){
				if(i != 1){
					out.println(" , ");
				}
				if(i == page){
					out.println(i);
				}
				else{
					out.println("<a href=\"http://localhost:8080/IRProject/result?query=" + query + "&page=" + i +"\">" + i + "</a>");
				}
			}
			out.println(" >> <br>");
		} catch (ParseException e) {
			e.getStackTrace();
		}

        out.println("<br><form action=\"http://localhost:8080/IRProject/index\">");
        out.println("<input type=\"submit\" value=\"Back to search\">");
        out.println("</form>");
	   		
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
