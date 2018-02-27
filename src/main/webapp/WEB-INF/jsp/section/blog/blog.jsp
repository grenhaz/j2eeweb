<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.blog" var="title" />
<ui:layout title="${title}">
    <h1>${title}</h1>
    
    <div class="row">
        <div class="col-sm-8">
            <!-- BLOGS -->
            <div class="row">
                <c:forEach var="post" items="${posts}">
                    <div class="col-sm-4">
                        <div class="post">
                            <div class="title"><a href="<c:url value="/blog/${post.id}" />"><c:out value="${post.title}" /></a></div>
                            <div class="content"><c:out value="${post.shortContent}" /></div>
                            <div class="publish"><c:out value="${post.formattedPublish}" /></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-4">
            <!-- LAST POSTS -->
            <h4>RECENT POSTS</h4>
            <c:forEach var="post" items="${posts.subList(0, posts.size() >= 5 ? 5 : posts.size())}">
                <div><a href="<c:url value="/blog/${post.id}" />"><c:out value="${post.title}" /></a></div>
            </c:forEach>
            <p></p>
            <!-- LAST COMMENTS -->
            <h4>LAST COMMENTS</h4>
            <p></p>
            <!-- INFO -->
            <h4>CONTACT US</h4>
            <p>Come Have Coffee with Us.</p>
            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium</p>
        </div>
    </div>
</ui:layout>