<#import "/spring.ftl" as spring/>
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<h1>Registration</h1>

    <form action="/registration" method="post">
        <div><label> Username : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <div><input type="submit" value="Registration"/></div>
    </form>

</@c.page>