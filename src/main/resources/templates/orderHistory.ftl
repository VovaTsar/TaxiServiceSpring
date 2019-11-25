<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<@c.page>


<#if success??>
    success
</#if>

    <div>
        <p>${rca.getMessage("message.history")}</p>

    </div>
    <div>

<#if !page.content?has_content>
    ${rca.getMessage("message.noorders")}

<#else >
    <table class="table">
        <thead>
        <tr>
            <td class="col-lg-2"><b>${rca.getMessage("message.initial")}</b></td>
            <td class="col-lg-2"><b>${rca.getMessage("message.dest")}</b></td>
            <td class="col-lg-2"><b>${rca.getMessage("message.cartype")}</b></td>
            <td class="col-lg-2"><b>${rca.getMessage("message.make")}</b></td>
            <td class="col-lg-2"><b>${rca.getMessage("message.time")}</b></td>
            <td class="col-lg-2"><b>${rca.getMessage("message.price")}</b></td>


        </tr>
        </thead>
        <tbody>

        <#list page.content as d>
            <tr>
                <td>
                    ${d.destPlace}
                </td>
                <td>
                    ${d.initPlace}
                </td>
                <td>
                    ${d.carType}
                </td>
                <td>
                    ${d.carMake}
                </td>
                <td>
                    ${d.time}
                </td>
                <td>
                    ${d.price}
                </td>

            </tr>

        </#list>

        </tbody>
    </table>

    <@p.pager url page/>
</#if>

    </div>

    <div>
        <a href="/user/home">${rca.getMessage("message.home")}</a>
    </div>

</@c.page>