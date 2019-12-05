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


<%// used to place error message at top of page 
    String errorMessage = "";
    String message = "";
    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

    </table> 

    <H2>People on work site</H2>
    <table border="1">
        <tr>
            <th>FirstName</th>
            <th>SecondName</th>
            <th>Address</th>
            <th>Role</th>
            <th></th>
        </tr>
        <% //for (Person person : serviceFacade.getallUsers()) {
            Person person = new Person();
        %>
        <tr>
            <td><%=person.getFirstName()%></td>
            <td><%=person.getSecondName()%></td>
            <td><%=person.getAddress()%></td>
            <td><%=person.getRole()%></td>
            <td>
                <form action="./modifyuser.jsp" method="post">
                    <input type="hidden" name="personId" value="<%=person.getId()%>">
                    <input type="hidden" name="action" value="deletePerson">
                    <button type="submit" >Delete</button>
                    <button action="./modifyuser.jsp" method="post" type="submit" >Modify</button>
                </form> 
            </td>


        </tr>

        <%
        // }
%>
    </table> 
    <form action="./modifyuser.jsp" method="post">
        <input type="hidden" name="personTypeStr" value="nurse">
        <button type="submit" >Add Nurse</button>
    </form> 
    <form action="./modifyuser.jsp" method="post">
        <input type="hidden" name="personTypeStr" value="client">
        <button type="submit" >Add Client</button>
    </form> 

</body>
</html>
