<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form:form commandName="login" method="POST" action="LogIn" class="navbar-form pull-right">
                    <form:input path="user" class="span2" placeholder="Username" required="True" />
                    <form:password path="pass" class="span2" placeholder="Password" required="True" />
                    <form:button type="submit" class="btn">Sign in</form:button>
                    <form:errors path="*" />
                </form:form>
            </div><!--/.nav-collapse -->
            
        </div>
    </div>
</div>