<%@tag description="Footer" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@configProperties.getProperty('footer.facebook')" var="facebook" />
<spring:eval expression="@configProperties.getProperty('footer.google')" var="google" />
<spring:eval expression="@configProperties.getProperty('footer.twitter')" var="twitter" />
<spring:eval expression="@configProperties.getProperty('footer.skype')" var="skype" />
<spring:eval expression="@configProperties.getProperty('footer.instagram')" var="instagram" />

<footer>
    <div class="row">
        <div class="col-sm-8">
            <spring:message code="copyright" />
        </div>
        <div class="col-sm-4 social-networks">
            <c:if test="${not empty facebook}">
                <a href="${facebook}" title="Facebook" alt="Facebook"><i class="fa fa-facebook-square"></i></a>
            </c:if>
            <c:if test="${not empty google}">
                <a href="${google}" title="Google+" alt="Google+"><i class="fa fa-google-plus"></i></a>
            </c:if>
            <c:if test="${not empty twitter}">
                <a href="${twitter}" title="Twitter" alt="Twitter"><i class="fa fa-twitter-square"></i></a>
            </c:if>
            <c:if test="${not empty skype}">
                <a href="${skype}" title="Skype" alt="Skype"><i class="fa fa-skype"></i></a>
            </c:if>
            <c:if test="${not empty instagram}">
                <a href="${instagram}" title="Instagram" alt="Instagram"><i class="fa fa-instagram"></i></a>
            </c:if>
        </div>
    </div>
</footer>