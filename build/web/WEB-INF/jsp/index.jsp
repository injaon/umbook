<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="model.Months"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("months", Months.values());
    request.setAttribute("curYear", Calendar.getInstance().get(Calendar.YEAR));
%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="<c:url value="/static/js/test.js"/>"></script>
    </head>
    <body>
        <div id="register">
            <h1>Register new human being</h1>
            <form:form commandName="signup" method="POST" action="SignUp" >
                <table>
                    <tr>
                        <td><form:label path="user">Nombre de Usuario</form:label></td>
                        <td><form:input path="user" required="True" /></td>
                        <td><form:errors path="user" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email</form:label></td>
                        <td><form:input path="email" required="True" /></td>
                        <td><form:errors path="email" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="nombre">Nombre</form:label></td>
                        <td><form:input path="nombre"  /></td>
                        <td><form:errors path="nombre" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="apellido">Apellido</form:label></td>
                        <td><form:input path="apellido" required="True" /></td>
                        <td><form:errors path="apellido" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="password">Contraseña</form:label></td>
                        <td><form:password path="password" required="True" /></td>
                        <td><form:errors path="password" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="repassword">Reescriba Contraseña</form:label></td>
                        <td><form:password path="repassword" required="True" /></td>
                        <td><form:errors path="repassword" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="genero">Genero</form:label></td>
                            <td colspan="2">
                            <form:radiobutton path="genero" value="true" label="M" />
                            <form:radiobutton path="genero" value="false" label="F"/>
                        </td>
                    </tr>
                    <!--                    TODO-->
                    <!--                    <tr><td>Foto Perfil</td><td><input type="file" name="foto"  accept="image/jpeg" /></td></tr>-->
                    <!--                    <input type="hidden" name="todo" value="upload">-->
                    <tr>
                        <td>Fecha de Nacimiento</td>
                        <td>
                            <form:select path="day">
                                <c:forEach begin="1" end="31" var="day">
                                    <form:option label="${day}" value="${day}" />
                                </c:forEach>
                            </form:select>
                            <form:select path="month">
                                <c:forEach items="${months}" var="month">
                                    <form:option label="${month}" value="${month}" />
                                </c:forEach>
                            </form:select>
                            <form:select path="year" >
                                <c:forEach begin="0" end="100" var="year">
                                    <form:option label="${curYear - year}" value="${curYear - year}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td><form:errors path="month" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="Registrarme!" /></td>
                    </tr>    
                </table>
            </form:form>
        </div>

        <hr />
        <div id="login-div">
            <h1>Login</h1>
            <form:form commandName="login" method="POST" action="LogIn">
                <table>
                    <tr>
                        <td><form:label path="user" >Username</form:label></td>
                        <td><form:input path="user" required="True" /></td>
                        <td><form:errors path="user" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="pass" >Password</form:label></td>
                        <td><form:password path="pass" required="True" /></td>
                        <td><form:errors path="pass" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="Login" /></td>
                    </tr>    
                </table>
            </form:form>

        </div>
    </body>
</html>