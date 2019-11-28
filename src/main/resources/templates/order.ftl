<#import "parts/common.ftl" as c>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<@c.page>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <#if !cars??>
    <div><h3>${rca.getMessage("message.discount")} : ${discount}%</h3></div>
        <div  ng-app="selectboxApp" ng-controller="selectboxCtrl">
            <#if error??>
                ${rca.getMessage("message.choosedif")}
            </#if>
    <form action="/user/order" method="post"  name="selectForm">
        <div>
            <label> ${rca.getMessage("message.initial")} <select name="initPlace" ng-model="placeStart" required >
                    <option value="">choose 1 street</option>
                    <#list streets as s >
                        <option  value="${s}">
                            ${s}
                        </option>
                    </#list>
                </select></label></div>

        <div>
            <div>
                <label> ${rca.getMessage("message.dest")} <select name="destPlace"  ng-model="placeEnd" required >
                        <option value="">choose 2 street</option>
                        <#list streets as s >
                            <option value="${s}">
                                ${s}
                            </option>
                        </#list>
                    </select></label></div>
            <div>
                <label> ${rca.getMessage("message.type")} <select name="carType"  ng-model="typeCar"  required>
                        <option value="">type</option>
                        <#list types as t >
                            <option value="${t}" >
                                ${t}
                            </option>
                        </#list>
                    </select></label></div>




            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
                <input type="submit" value="${rca.getMessage("message.confirm")}" ng-disabled="selectForm.initPlace.$invalid || selectForm.destPlace.$invalid || selectForm.carType.$invalid" id="mysubmit"/>


            </div>

            </div>
    </form>


        </div>


    <#elseif cars?? && !car??>

        <form action="/user/order" method="post">
            <div>
                <label> ${rca.getMessage("message.car")} </label>
                        <table class="table">
                            <thead>
                                <tr>
                                    <td><b>${rca.getMessage("message.make")}</b></td>
                                    <td><b>${rca.getMessage("message.place")}</b></td>

                                    <td hidden></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#list cars as car>
                                    <tr>
                                        <td>
                                            ${car.carMake}
                                        </td>
                                        <td>
                                            ${car.place}
                                        </td>

                                        <td>
                                            <input type="radio" name="carId" value="${car.id}"  />

                                        </td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
            <input type="hidden" name="carType" value="${RequestParameters.carType}" />
            <input type="hidden" name="initPlace" value="${RequestParameters.initPlace}" />
            <input type="hidden" name="destPlace" value="${RequestParameters.destPlace}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="${rca.getMessage("message.choose")}" />
        </form>

<#else>
    <br>

    chosen car: ${car.carType} ${car.carMake}
    order time: ${rideTime}
    price: ${price}
        $ time: ${waitTime}

    <form action="/user/history" method="post">
        <input type="hidden" name="carId" value="${RequestParameters.carId}" />
        <input type="hidden" name="addressId" value="${addressId.id}" />
        <input type="hidden" name="price" value="${price}" />
        <input type="hidden" name="waitTime" value="${waitTime}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="${rca.getMessage("message.confirm")}"/>
    </form>
</#if>

    <form action="/user/home" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div>
            <input type="hidden" name="cancel" value="cancel" />
            <input type="submit" value="${rca.getMessage("message.cancel")}" />

        </div>
    </form>


</@c.page>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>