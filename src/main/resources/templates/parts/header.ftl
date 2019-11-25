<#import "/spring.ftl" as spring/>
<section class="section-1 head_wrapper">
    <header class="header container">
        <div class="headline row ">
            <div class="logo col-lg-2">
                 <img class="logo-img" src="<@spring.url '/sources/taxi-logo.png'/>" alt="logo">
            </div>
            <div class="head_info col-lg-8">
                <h1>${rca.getMessage("message.taxi")}</h1>
            </div>
            <div class = "col-lg-2 langs">
                <div class="  lang-images">
                <input type="image" src="<@spring.url '/sources/ua-flag.png'/>" alt="UA" onclick="switchUA();" />
                <input type="image" src="<@spring.url '/sources/us-flag.png'/>" alt="US" onclick="switchUS();" />
                </div>
                <a href="/logout"><button type="button" class="btn btn-info btn-sm logout-button">${rca.getMessage("message.logout")}</button></a>
            </div>
        </div>
    </header>
</section>
