<#import "parts/common.ftl" as c>
<@c.page>

    <#if success_reg??>
       <p class="successmsg center-text"> ${rca.getMessage("message.successreg")}</p>
    </#if>
    <div class="index col-lg-12">
         <h1 class="center-text">${rca.getMessage("message.welcome")}</h1>
        <a href="/login"><button type="button" class="btn-lg btn-info">${rca.getMessage("message.login")}</button></a>
        <a href="/register"><button type="button" class="btn-lg btn-info">${rca.getMessage("message.register")}</button></a></div>

</@c.page>
