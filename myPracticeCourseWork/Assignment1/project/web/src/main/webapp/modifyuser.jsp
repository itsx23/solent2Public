<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.project.model.dto.Appointment"%>
<%@page import="org.solent.com504.project.model.dto.Person"%>
<%@page import="org.solent.com504.project.model.dto.Role"%>
<%@page import="org.solent.com504.project.model.service.ServiceFacade"%>
<%@page import="org.solent.com504.project.impl.web.WebObjectFactory"%>

<%
     // used to place error message at top of page 
    String errorMessage = "";
    String message = "";
    
    
    // accessing request parameters
    String personTypeStr = request.getParameter("personTypeStr");

    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <H1>Creating new <%=personTypeStr%> </H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>

        <form action="./modifyuser.jsp" method="post">
            <input type="hidden" name="personType" value="<%=personTypeStr%>">
            <input type="hidden" name="action" value="addPerson">
            Enter new <%=personTypeStr%> Name:  <input type="text" name="firstName">
            <button type="submit" >Create <%=personTypeStr%></button>
        </form> 
        <BR>
        <form action="./userlist.jsp">
            <button type="submit" >Return To User List</button>
        </form> 

    </body>
</html>
