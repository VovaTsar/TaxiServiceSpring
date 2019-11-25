<#import "parts/common.ftl" as c>


<@c.page>
    <h1 class="reg-text center-text">${rca.getMessage("message.register")}</h1>
    <#if message??>
        ${message}
    </#if>
<div ng-app="ngpatternApp" ng-controller="ngpatternCtrl" class="form-group">
    <form action="/register" method="post" class="form-group" name="regForm" ng-submit="regForm.$valid  " novalidate >
        <div class="row">
            <label class="col-lg-offset-4 col-lg-5 text-left"> User Name : <br/><br /> <input type="text" name="login" ng-model="txtlogin" ng-pattern="/^[A-Za-z0-9]{4,15}$/" required /></label>

            <span style="color:Red" ng-show="regForm.login.$touched&&regForm.login.$error.required"> Required! </span>

            <span style="color:Red" ng-show="regForm.login.$touched&&regForm.login.$error.pattern">Wrong login</span>

            <br /><br /> <br /><br />
        </div>
        <div class="row">
        <label class="col-lg-offset-4 col-lg-5 text-left"> Password :<br/><br />  <input type="password" name="password" ng-model="txtpw" ng-pattern="/^[A-Za-z0-9]{6,15}$/" required /></label>

        <span style="color:Red" ng-show="regForm.password.$touched&&regForm.password.$error.required"> Required! </span>

        <span style="color:Red" ng-show="regForm.password.$touched&&regForm.password.$error.pattern">Wrong password</span>

        <br /><br /> <br /><br />
        </div>

        <br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div  class="col-lg-4 col-lg-offset-4 b">
        <button ng-disabled="regForm.login.$invalid || regForm.password.$invalid" type="submit"  class="text-center btn-lg btn-info">Submit Form</button><br /><br />
        </div>


    </form>
</div>
</@c.page>
