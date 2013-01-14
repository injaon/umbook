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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UMBook</title>
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
        <link href="<c:url value="/css/datepicker.css"/>" rel="stylesheet" media="screen">
        <link href="<c:url value="/css/bootstrap-select.min.css"/>" rel="stylesheet" media="screen">

        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            .form-signin .form-signin-heading,
            .form-signin .checkbox {
                margin-bottom: 10px;
            }
            .form-signin input[type="text"],
            #genero, #signup.errors,
            .form-signin input[type="password"] {
                font-size: 16px;
                height: auto;
                width: 300px;
                margin-bottom: 15px;
                padding: 7px 9px;
            }

            #datepicker {
                width: 280px
            }

        </style>

    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">UMBook</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li><a href="#about">About</a></li>
                        </ul>
                        <form:form commandName="login" method="POST" action="LogIn" class="navbar-form pull-right">
                            <form:input path="user" class="span2" placeholder="Username" required="True" />
                            <form:password path="pass" class="span2" placeholder="Password" required="True" />
                            <form:button class="btn">Sign in</form:button>
                            <form:errors path="*" />
                        </form:form>
                    </div><!--/.nav-collapse -->

                </div>
            </div>
        </div>


        <div class="container">
            <form:form commandName="signup" method="POST" action="SignUp" class="form-signin">
                <h2 class="form-signin-heading">Sign up, Gato</h2>
                <form:input path="nombre" class="input-block-level" placeholder="First Name" required="True" />
                <form:input path="apellido" class="input-block-level" placeholder="Last Name" required="True" />
                <form:input path="email" class="input-block-level" placeholder="Email address" required="True" />
                <form:input path="user" class="input-block-level" placeholder="Username" required="True" />
                <form:password path="password" class="input-block-level" placeholder="Password" required="True" />
                <form:password path="repassword" class="input-block-level" placeholder="Re-Password" required="True" />
                <form:input path="birth" id="datepicker" placeholder="bithdate"/>
                <form:select path="genero" cssClass="selectpicker" placeholder="Re-Password">
                    <form:option value="Male">Male</form:option>
                    <form:option value="Female">Female</form:option>
                </form:select>
                <form:errors cssClass="input-block-level" path="*" />
                <form:button class="btn btn-large btn-primary">Sign up</form:button>
            </form:form>
        </div> <!-- /container -->

        <!-- Le JavaScripts-->
        <script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap-datepicker.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap-select.min.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/index.js"/>" ></script>
    </body>
</html>