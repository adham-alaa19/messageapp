 <%@page import="com.iti.managers.session.SessionManager"%>
<%@page import="com.iti.models.messages.Pub_API_INFO"%>
<%@ page import="java.util.List"   %> 
<%
    // Retrieve the list of API names
    List<Pub_API_INFO> apiList = SessionManager.getSessionApiInfoList(request);
    int curretnApi =  SessionManager.getSessionApiInfo(request);
    boolean isApiListEmpty = (apiList == null || apiList.isEmpty());
%>

<nav class="navbar navbar-expand-lg shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand" href="home">Messager Client</a>
        <div class="vertical-line"></div>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="profile">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="send">Send</a></li>
                <li class="nav-item"><a class="nav-link" href="history">History</a></li>
            </ul>

            <div class="d-flex align-items-center  api-container">
                <label for="apiSelect" class="me-2 text-dark fw-bold">API:</label>
                <select id="apiSelect" placeholder="Select API" class="form-select api-dropdown" <%= isApiListEmpty ? "disabled" : "" %>>
                    <% if (!isApiListEmpty) { %>
                        <% for (Pub_API_INFO api : apiList) { %>
                        <%
                         String frst5num = api.getSender_id().substring(0, 5);
                         String apiname = api.getApi_name().substring(0, 6);
                        %>
                       <% if (api.getApi_id()==curretnApi) { %>
                            <option selected value="<%= api.getApi_id() %>"><%= apiname %> :+<%= frst5num %>..</option> 
                             <% } else { %>
                               <option  value="<%= api.getApi_id() %>"><%= apiname %> :+<%= frst5num %>..</option> 
                                 <% } %>
                        <% } %>
                    <% } else { %>
                        <option value="addApi" disabled selected>Add API Info</option>
                    <% } %>
                </select>
            </div>

            <ul class="navbar-nav ms-auto">
                <li class="nav-item d-flex align-items-center">
                    <div class="vertical-line"></div>
                    <a class="nav-link text-danger" href="../../logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script src="../../static/js/customer_pages/changeApi.js"></script> 
