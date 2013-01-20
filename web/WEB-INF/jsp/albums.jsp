<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- modal -->
<div id="modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">INSERT INTO albums VALUE ...</h3>
    </div>
    <c:url value="/album/new" var="modalAction" />
    <form:form commandName="albumForm" action="${modalAction}" method="POST">
        <div class="modal-body">
            <div class="row">
                <div class="span3">
                    <form:input path="name" placeholder="Album title" required="True" />        
                    <div class="row">
                        <div class="span3">
                            <form:textarea path="description" placeholder="Say something about the album"/> 
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="id" />
        </div>
        <div class="modal-footer">
            <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Roll-Back</button>
            <form:button  class="btn btn-primary">Commit</form:button>
            </div>
    </form:form>
</div>

<!-- albums -->
<div id="albums-container" class="container-fluid">
    <c:set value="${0}" var="i"/>
    <div class="row-fluid">
        <ul class="thumbnails">
            <!-- new album -->
            <c:if test="${owner == user}">
                <li class="span4">
                    <div class="thumbnail">
                        <a href="" class="photo new-album-link">
                            <img src="${newAlbum.image}" />  
                        </a>
                        <h3><a class="new-album-link" href="">${newAlbum.name}</a></h3>
                        <p>${newAlbum.description}</p>    
                    </div>
                </li>
                <c:set value="${1}" var="i"/>
            </c:if>
            <c:forEach items="${albums}" var="album">
                <c:if test="${(i mod 3) eq 0}" >
                    <c:if test="${i gt 0}">
                    </ul></div>
                </c:if>
            <div class="row-fluid">
                <ul class="thumbnails">
                </c:if>
                <li class="span4">
                    <div class="thumbnail">
                        <a href="<c:url value="/phto/album/${album.id}/all/" />" class="photo">
                            <img src="${album.image}" />  
                        </a>
                        <h3><a href="<c:url value="/phto/album/${album.id}/all/" />">${album.name}</a></h3>
                        <p>${album.description}</p>    
                    </div>
                </li>
                <c:set value="${i +1}" var="i"/>
            </c:forEach>
        </ul>
    </div><!--/row-->
</div>