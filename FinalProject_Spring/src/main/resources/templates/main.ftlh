<#import "/spring.ftl" as spring/>
<#import "parts/common.ftlh" as c>
<@c.page>
<div class="header_top">
    <div class="locale">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-warning" type="button"><a href="?locale=uk">UKR</a></button>
            <button class="btn btn-warning" type="button"><a href="?locale=en">ENG</a></button>
        </div>
    </div>
</div>
<div class="header_container">
    <h1 class=name_company><@spring.message code='name.company'/></h1>


    <button class="btn btn-warning " type="button">
        <a class="button_log_in" href="/user" ><@spring.message code='login.text'/></a>
    </button>
    <span> | </span>
    <button class="btn btn-warning " type="button">
        <a class="button_log_in" href="/registration" ><@spring.message code='registration'/></a>
    </button>
</div>
</@c.page>