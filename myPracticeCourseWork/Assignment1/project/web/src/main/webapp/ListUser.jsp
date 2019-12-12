<%@page import="java.util.ArrayList"%>
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
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();

    // get request values
    String action = (String) request.getParameter("action");
    String personIdReq = (String) request.getParameter("personId");
    String personFirstName = (String) request.getParameter("firstName");
    String personSecondName = (String) request.getParameter("secondName");
    String personAddress = (String) request.getParameter("address");
    String personRole = (String) request.getParameter("role");

    String errorMessage = "";
    String message = "";

    if ("deletePerson".equals(action)) {
        try {
            Long personId = Long.parseLong(personIdReq);
            serviceFacade.deletePerson(personId);
        } catch (Exception e) {
            errorMessage = "problem deleting Person " + e.getMessage();
        }
    } else if ("modifyPerson".equals(action)) {
        try {
            Long personId = Long.parseLong(personIdReq);
            Person personTemplate = serviceFacade.retrievePersonById(personId);
            if (personTemplate == null) {
                errorMessage = "problem modifying Person. could not find personId " + personId;
            } else {
                personTemplate.setId(personId);
                personTemplate.setFirstName(personFirstName);
                personTemplate.setSecondName(personSecondName);
                personTemplate.setAddress(personAddress);
                if (personRole == "careworker") {
                    personTemplate.setRole(Role.CAREWORKER);
                } else {
                    personTemplate.setRole(Role.PATIENT);
                }
                Person person = serviceFacade.updatePerson(personTemplate);
                if (person == null) {
                    throw new IllegalStateException("person should not be null");
                }
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Person " + e.getMessage();
        }
    } else if ("createPerson".equals(action)) {
        try {
            Person personTemplate = new Person();
            personTemplate.setFirstName(personFirstName);
            personTemplate.setSecondName(personSecondName);
            personTemplate.setAddress(personAddress);
            if (personRole == "careworker") {
                personTemplate.setRole(Role.CAREWORKER);
            } else {
                personTemplate.setRole(Role.PATIENT);
            }
            Person person = serviceFacade.createPerson(personTemplate);
            if (person == null) {
                errorMessage = "problem creating Person. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Person " + e.getMessage();
        }
    }

    List<Person> personList = serviceFacade.findallPersons();
%>

<!DOCTYPE html>
<html>
    <body>
        <!-- print error message if there is one -->
        <div style="color: red;"><%=errorMessage%></div>

        <h1>Person List</h1>
        <table>
            <tr>
                <th>Person ID</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Address</th>
                <th>Role</th>

            </tr>
            <%
                for (Person person : personList) {
            %>
            <tr>
                <td><%=person.getId()%></td>
                <td><%=((person.getFirstName() == null) ? "" : person.getFirstName())%></td>
                <td><%=((person.getSecondName() == null) ? "" : person.getSecondName())%></td>
                <td><%=((person.getAddress() == null) ? "" : person.getAddress())%></td>
                <td><%=((person.getRole() == null) ? "" : person.getRole())%></td>
                <td>
                    <form action="AddOrModifyUser.jsp">
                        <input type="hidden" name="action" value="modifyPerson"> <input
                            type="hidden" name="personId" value="<%=person.getId()%>"> <input
                            type="submit" value="Modify Person">
                    </form>
                    <form action="ListUser.jsp">
                        <input type="hidden" name="action" value="deletePerson"> <input
                            type="hidden" name="personId" value="<%=person.getId()%>"> <input
                            type="submit" value="Delete Person">
                    </form>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <BR>
        <form action="AddOrModifyUser.jsp">
            <input type="hidden" name="action" value="createPerson"> <input
                type="submit" value="Create Person">
        </form>

    </body>
</html>
