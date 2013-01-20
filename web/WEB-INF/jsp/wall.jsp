<%-- 
Copyright (C) 2013 Gabriel Lopez <gabriel.marcos.lopez@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UMBook | ${owner.fullName}</title>
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen" >
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }
            .perfil {
                max-width: 900px;
                padding: 19px 29px 29px;
                margin: 0 auto 0;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            .one-line-form input {
                margin-top: 0px;
            }
            #main-container {
                max-width: 800px;
                padding: 19px 29px 29px;
                margin: 0 auto 0;
            }
            #comments-container {
                max-width: 500px; 
                padding: 19px 29px 29px;
                margin: 0 auto 0;
            }
            .comment-box {
                max-width: 400px;
                margin: 0 auto 20px;
            }
            .sidebar-nav {
                padding: 9px 0;
                margin: 0 auto 0;
            }            
        </style>
    </head>
    <body>
        <!-- navbar -->
        <c:import url="navbar.jsp" />        
        <!-- Owner box -->
        <c:import url="wallOwnerBox.jsp" />        
        <div id="main-container" class="container-fluid">
            <div id="comments-container">
                <!-- Comments -->
                <c:if test="${empty owner.commentsOnWall}" >
                    /* No Comments */
                </c:if>

                <c:url value="/comment/comment" var="commentComment"/>
                <c:forEach items="${owner.commentsOnWall}" var="comment" >
                    <div class="row-fluid comment-box">
                        <img class="span3"  src="<c:url value="${comment.origin.photo}"/>"/>
                        <div class="span9">
                            <a href="<c:url value="/wall/${comment.origin.id}"/>"><h4 class="origin-name">${comment.origin.fullName}</h4></a>
                            <!--<div class="row">${comment.date}</div>-->
                            <div class="row-fluid comment-body">
                                <c:out value="${comment.body}" />
                            </div>
                            <div class="row-fluid">
                                <form:form cssClass="navbar-form one-line-form" commandName="commentForm" method="POST" action="${commentComment}" >
                                    <form:hidden path="destiny" value="${comment.id}" />
                                    <form:input path="body" placeholder="Write a comment..." required="True"/>
                                    <form:button class="btn-submit btn-primary btn" >Post</form:button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--Le JavaScripts-->
        <script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/wall.js"/>" ></script>
    </body>
</html>
