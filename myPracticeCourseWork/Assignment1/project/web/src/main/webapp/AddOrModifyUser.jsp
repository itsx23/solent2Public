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

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // get request values
    String action = (String) request.getParameter("action");
    String personIdReq = (String) request.getParameter("personId");
    String personFirstName = (String) request.getParameter("firstName");
    String personSecondName = (String) request.getParameter("secondName");
    String personAddress = (String) request.getParameter("address");
    String personRole = (String) request.getParameter("role");

    String errorMessage = "";
    String message = "";

    Person person = null;
    Integer personId = null;

    if ("modifyPerson".equals(action)) {
        try {
            personId = Integer.parseInt(personIdReq);
            person = serviceFacade.retrievePerson(person);
        } catch (Exception e) {
            errorMessage = "problem finding person " + e.getMessage();
        }
    } else if ("createPerson".equals(action)) {
        try {
            person = new Person();
        } catch (Exception e) {
            errorMessage = "problem finding person " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Person</title>
    </head>
    <body>
        <% if ("createPerson".equals(action)) {
        %>
        <h1>Add New Person</h1>
        <% } else {%>
        <h1>Modify Person <%=personId%></h1>
        <% }%>
        <form action="ListUser.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Person Id</td>
                    <td><%=person.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>firstname</td>
                    <td><%=((person.getFirstName() == null) ? "" : person.getFirstName())%></td>
                    <td><input type="text" name="firstname" value ="<%=((person.getFirstName() == null) ? "" : person.getFirstName())%>"></td>
                </tr>
                <tr>
                    <td>secondname</td>
                    <td><%=((person.getSecondName() == null) ? "" : person.getSecondName())%></td>
                    <td><input type="text" name="secondname" value ="<%=((person.getSecondName() == null) ? "" : person.getSecondName())%>"></td>
                </tr>
                <tr>
                    <td>address</td>
                    <td><%=((person.getAddress() == null) ? "" : person.getAddress())%></td>
                    <td><input type="text" name="isbn" value ="<%=((person.getAddress() == null) ? "" : person.getAddress())%>"></td>
                </tr>
            </table> 
            <BR>
            <% if ("createPerson".equals(action)) {
            %>
            <input type="hidden" name="action" value="createPerson">
            <input type="hidden" name="personId" value="<%=personId%>">
            <input type="submit" value="Create New Person">
            <% } else if ("modifyPerson".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyPerson">
            <input type="hidden" name="personId" value="<%=personId%>">
            <input type="submit" value="Modify Person">
            <% }%>
        </form>
        <form action="ListUser.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>