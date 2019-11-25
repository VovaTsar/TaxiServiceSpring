<#import "parts/common.ftl" as c/>
<#import "spring.ftl" as spring/>
<@c.page>

    <div class="container">
        <div class="row">
            <div class="col-lg-offset-2 col-lg-8 ">
                <div class="well well-sm">
                    <div class="row">
                        <div class="col-lg-5 ">
                            <img src="<@spring.url '/sources/profile-pic.png'/>" alt="profile-pic" class="img-rounded img-responsive profile-img" />
                        </div>
                        <div class="col-lg-offset-1 col-lg-6">
                            <br />
                            <h4>
                                ${client.login}</h4>
                            <br />
                            <p>
                                ${rca.getMessage("message.role")} : ${client.role}
                                <br />

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div class="col-lg-offset-2 col-lg-8">
    <a href="/admin/cars"><button type="button" class="btn-lg btn-info">${rca.getMessage("message.cars")}</button></a>
    <a href="/admin/clients"><button type="button" class="btn-lg btn-info">${rca.getMessage("message.clients")}</button></a>
    </div>
</@c.page>
