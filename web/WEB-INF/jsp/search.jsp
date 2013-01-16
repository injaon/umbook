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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
        <title>UMBook | Search </title>
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }
            #result-container {
                max-width: 1000px;
                padding: 19px 29px 29px;
                margin: 0 auto 0;
            }

        </style>
    </head>
    <body>
        <c:import url="navbar.jsp" />
        <div id="result-container">
            <c:if test="${empty users}">
                <div class="alert alert-block">
                    <h4>Buuu! </h4>
                    We cannot find any person!
                </div>
            </c:if>
            <c:set value="${0}" var="i"/>
            <c:forEach items="${users}" var="u">
                <c:if test="${(i mod 3) eq 0}" >
                    <c:if test="${i gt 0}">
                    </ul></div>
                </c:if>
            <div class="row-fluid">
                <ul class="thumbnails">
                </c:if>
                <li class="span4">
                    <div class="thumbnail">
                        <img style="width: 256px; height: 256px;" src="<c:url value="${u.photo}"/>">
                        <div class="caption">
                            <a href="<c:url value="/wall/${u.id}"/>"><h3>${u.fullName}</h3></a>
                            <p>Email: ${u.email}</p>
                            <p>Birth: ${u.birth}</p>
                            <p><a href="#" class="btn btn-success">Add</a> <a href="#" class="btn btn-danger pull-right">Remove</a></p>
                        </div>
                    </div>
                </li>
                <c:set value="${i +1}" var="i"/>
            </c:forEach>
        </ul></div>

</div>

<!-- Le JavaScripts-->
<script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap-datepicker.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap-select.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/seach.js"/>" ></script>
</body>
</html>
