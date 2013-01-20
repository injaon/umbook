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
<div class="container-fluid perfil" >
    <div class="row-fluid">
        <div class="well sidebar-nav span3 sidebar">
            <ul class="nav nav-list">
                <li class="nav-header">Options</li>
                <li class="active"><a id="wall-link" href="">Wall</a></li>
                <li><a id="photos-link" href="">Photos</a></li>
            </ul>
        </div><!--/.well -->
        <div class="container-fluid span8">
            <div class="row-fluid">
                <div class="span4">
                    <img src="<c:url value="${owner.photo}"/>"/>        
                </div>
                <div class="span7 offset1">
                    <h2>${owner.fullName}</h2>
                    <p>Email: ${owner.email}</p>
                    <p>Birth: ${owner.birth}</p>
                    <form:form cssClass="navbar-form one-line-form" commandName="commentForm" method="POST" action="comment" id="new-comment" >
                        <form:hidden path="destiny" value="${owner.id}" />
                        <c:if test="${owner==user}">
                            <form:input path="body" required="True" placeholder="En que estas pensando?"/>
                        </c:if>    
                        <c:if test="${owner!=user}">
                            <form:input path="body" required="True" placeholder="Write something..."/>        
                        </c:if>
                        <form:button class="btn-submit btn-primary btn" >Post</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
                        
                        