
<#import "parts/common.ftl" as c>
<@c.page>
    <h1 class="login-text center-text">${rca.getMessage("message.loginpage")}</h1>

    <#if logout>
        logout
    </#if>
    <#if error>
        User name or password are not correct!
    </#if>


    <div ng-app="ngpatternApp" ng-controller="ngpatternCtrl" class="login-form center col-lg-12">



        <form action="/login" class="form-group" method="post" id="loginForm" name="loginForm" ng-submit="loginForm.$valid" novalidate >
<div class="row">
            <label class="col-lg-offset-4 col-lg-5 text-left"> User Name :<br/><br /> <div><input class="input-log" type="text" name="username" ng-model="txtlogin" ng-pattern="/^[A-Za-z0-9]{3,15}$/" required /></div></label>

         <div>  <span style="color:Red"  ng-show="loginForm.username.$touched&&loginForm.username.$error.required"> Required! </span>

            <span style="color:Red" ng-show="loginForm.username.$touched&&loginForm.username.$error.pattern">Wrong login</span>
         </div>
</div>
            <br /><br />
            <div class="row">
            <label class="col-lg-offset-4 col-lg-5 text-left"> Password :<br/><br/> <input class="input-log" type="password" name="password" ng-model="txtpw" ng-pattern="/^[A-Za-z0-9]{6,15}$/" required /></label>

            <span style="color:Red" ng-show="loginForm.password.$touched&&loginForm.password.$error.required"> Required! </span>

            <span style="color:Red" ng-show="loginForm.password.$touched&&loginForm.password.$error.pattern">Wrong pw</span>
            </div>
            <br />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button ng-click="submit($event)" class="btn-lg btn-info" ng-disabled="loginForm.username.$invalid || loginForm.password.$invalid" >${rca.getMessage("message.submit")}</button><br /><br />



        </form>

    </div>


</@c.page>
