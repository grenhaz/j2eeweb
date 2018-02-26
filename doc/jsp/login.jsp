<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<ui:layout title="Login">
    <div class="container">
        <form name='f' action="login" method='POST'>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table>
               <tr>
                  <td>User:</td>
                  <td><input type='text' name='username' value=''></td>
               </tr>
               <tr>
                  <td>Password:</td>
                  <td><input type='password' name='password' /></td>
               </tr>
               <tr>
                  <td><input name="submit" type="submit" value="submit" /></td>
               </tr>
            </table>
        </form>
    </div>
</ui:layout>