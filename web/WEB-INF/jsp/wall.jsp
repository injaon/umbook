<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="center">
            <div id="left">


                <img src="${owner.photo}"/>
                <a href="/gallery.jsp/${owner.id}">Fotos</a><br/>
                <a href="/info.jsp/${owner.id}">Informacion</a>
            </div>
        </div>
        <div id="right">
            <label>${owner.firstName} ${owner.lastName}</label>
            <!-- comment box -->
            <div>
                <form:form commandName="comment" method="POST" action="Comment" >
                    <form:hidden path="destiny" value="${owner.id}" />
                    <c:if test="${owner==user}">
                        <form:input path="body" value="En que estas pensando?"/>
                    </c:if>    
                    <c:if test="${owner!=user}">
                        <form:input path="body"/>        
                    </c:if>
                    <input type="submit" value="Publicar">
                </form:form>
            </div>
            <!--comments--> 
            <div id="comments">


                <!--model box for comments-->
                <!--                <div id="commentBox">
                                    <img class="userImg"></img> 
                                    <a class="userRealName"></a>
                                    <a class="comment"></a>
                                    <a class="time"></a>
                                    <hr />
                                </div>-->


            </div>
        </div>  
    </body>
</html>