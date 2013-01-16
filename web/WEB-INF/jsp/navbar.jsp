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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <!-- 
            TODO: notification for messages friendrequest
            -->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <a class="brand" href="#">UMBook</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">msg</a></li>
                    <li><a href="<c:url  value='/about'/>">About</a></li>
                </ul>
                <!--search form-->
                <c:url value="/search" var="searchAction" />
                <form:form cssClass="navbar-search" commandName="search" method="GET" action="${searchAction}">
                    <form:input path="input" cssClass="search-query" placeholder="Search for people" />
                </form:form>

                <ul class="nav pull-right">
                    <li class="nav"><a href="<c:url value="/wall/${user.id}"/>">${user.fullName}</a></li>
                    <li class="nav">
                        <div class="">
                            <a class="nav dropdown-toggle" data-toggle="dropdown">
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Exit</a></li>
                                <li class="divider"></li>
                                <li class="nav-header">Nav header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
                <!--
                                <a class="nav pull-right">${owner.fullName}</a>
                                <ul class="nav pull-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
                
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Action</a></li>
                                            <li><a href="#">Another action</a></li>
                                            <li><a href="#">Exit</a></li>
                                            <li class="divider"></li>
                                            <li class="nav-header">Nav header</li>
                                            <li><a href="#">Separated link</a></li>
                                            <li><a href="#">One more separated link</a></li>
                                        </ul>
                                    </li>
                                </ul>-->
            </div><!--/.nav-collapse -->

        </div>
    </div>
</div>

<%                            /*

     <li class="dropdown">
     <a>${owner.fullName}</a>
     <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
     <ul class="dropdown-menu">
     <li><a href="#">Action</a></li>
     <li><a href="#">Another action</a></li>
     <li><a href="#">Exit</a></li>
     <li class="divider"></li>
     <li class="nav-header">Nav header</li>
     <li><a href="#">Separated link</a></li>
     <li><a href="#">One more separated link</a></li>
     </ul>
     </li>
     */
%> 