<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quick Servlet Demo</title>
</head>
<body>
    <a href="/QuickServlet">Click here to send GET request</a>
     
    <br/><br/>
     
    <form action="QuickServlet" method="post">
        Width: <input type="text" size="5" name="width"/>
        &nbsp;&nbsp;
        Height <input type="text" size="5" name="height"/>
        &nbsp;&nbsp;
        <input type="submit" value="Calculate" />
    </form>
</body>
</html>