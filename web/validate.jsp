<%-- 
    Document   : validate
    Created on : 11/07/2018, 07:33:26 PM
    Author     : asanchez
--%>
<%@page import="javax.faces.application.FacesMessage"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    try {
            String username = request.getParameter("usuario");
            String password = request.getParameter("clave");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GestionCafeteria?"
                    +"user=root&password=");
            PreparedStatement ps = con.prepareStatement(
                    "Select usuario,clave from USUARIOSSESION where usuario=?"
                    + " and clave=?");
            ps.setString(1, username);
            ps.setString(2, password);
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
                  response.sendRedirect("faces/index.xhtml");
               }else{
               FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales invalidas.",
                                    "Login Error"));
           }
 
        } catch (Exception e) {
            response.sendRedirect("faces/userError.html");
        }
%>