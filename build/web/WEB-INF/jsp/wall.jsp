<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
        <link href="<c:url value="/css/wall.css"/>" rel="stylesheet" media="screen">
    </head>
    <body>

        <!-- Foto de perfil con box comment :D -->
        <div class="row">
            <div class="span3">
                <img src="<c:url value="${owner.photo}"/>"/>
            </div>
            <div class="span9">
                <h2>${owner.fullName}</h2>
                <!-- comment box -->
                <div>
                    <form:form commandName="comment" method="POST" action="Comment" class="new-comment" >
                        <form:hidden path="destiny" value="${owner.id}" />
                        <c:if test="${owner==user}">
                            <form:input path="body" placeholder="En que estas pensando?"/>
                        </c:if>    
                        <c:if test="${owner!=user}">
                            <form:input path="body" placeholder="Write something..."/>        
                        </c:if>
                        <form:button class="btn-submit btn-primary btn" type="submit" >Publicar</form:button>
                    </form:form>
                </div>
            </div>
        </div>  

        <!-- aca empiezan los comentarios y amigos -->

        <div class="container-fluid">
            <div class="row-fluid" >
                <div class="span1">
                    <!-- info -->
                    <div class="row"><a href="/gallery.jsp/${owner.id}">Fotos</a></div>
                    <div class="row"><a href="/info.jsp/${owner.id}">Informacion</a></div>
                    <!-- friends -->
                    <div class="row">Friends</div>
                    <c:forEach items="${owner.friends}" var="friend" >
                        <div class="row"> 
                            <a href="<c:url value="/wall/${friend.id}"/>">${friend.fullName}</a>
                        </div>
                    </c:forEach>

                </div>
                <div class="span11">
                    <!--Comments-->
                    <c:forEach items="${owner.commentsOnWall}" var="comment" >
                        <div class="row">
                            <div class="span1">
                                <img src="<c:url value="${comment.origin.photo}"/>"/>
                            </div>
                            <div class="span10">
                                <div class="row">
                                    <c:if test="${comment.origin eq comment.destiny}"><c:out value="${comment.origin.fullName}"/></c:if>
                                    <c:if test="${comment.origin ne comment.destiny}"><c:out value="${comment.origin.fullName}"/> -> <c:out value="${comment.destiny.fullName}"/></c:if>
                                </div>
                                <div class="row">${comment.date}</div>
                            </div>

                        </div>
                        <div class="row">
                            <c:out value="${comment.body}" />
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
    </body>
</html>