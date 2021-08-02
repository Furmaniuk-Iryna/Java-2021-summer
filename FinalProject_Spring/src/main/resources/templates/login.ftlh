<#import "/spring.ftl" as spring/>
<#import "parts/common.ftlh" as c>
    <#import "parts/login.ftlh" as l>
<#--<div if="${param.error}">-->
<#--    Invalid username and password.-->
<#--</div>-->
<#--<div if="${param.logout}">-->
<#--    You have been logged out.-->
<#--</div>-->
<@c.page>
<@l.login "/login" />


</@c.page>