<%@tag description="Comment" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="comment" required="true" type="org.obarcia.demo.models.article.Comment" rtexprvalue="true" %>
<div class="comment">
    ${comment.content}
</div>