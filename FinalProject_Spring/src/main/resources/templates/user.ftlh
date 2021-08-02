<#import "/spring.ftl" as spring/>
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>


    <h1>User</h1>
    <div class="header_top">
        <div class="locale">
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-warning" type="button"><a href="?locale=uk">UKR</a></button>
                <button class="btn btn-warning" type="button"><a href="?locale=en">ENG</a></button>
            </div>
        </div>
    </div>
    <button class="btn btn-warning " type="button">
        <a class="button_log_in" href="/logout">
            <@spring.message code='logout.text'/>
        </a>
    </button>

</@c.page>
