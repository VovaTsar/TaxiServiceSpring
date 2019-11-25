<#macro page>
    <#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Taxi app</title>
        <link rel="stylesheet" type="text/css"
              href="<@spring.url '/css/bootstrap.css'/>"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css"
              href="<@spring.url '/css/styles.css'/>"/>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script type="text/javascript">

            var app = angular.module('ngpatternApp', []);

            app.controller('ngpatternCtrl', function () {

            });

        </script>
        <script type="application/javascript">
            function switchUA() {
                window.location.replace(window.location.origin + window.location.pathname + '?lang=UA');
            }

            function switchUS() {
                window.location.replace(window.location.origin + window.location.pathname + '?lang=US');
            }
        </script>


    </head>
    <body>
    <#include "header.ftl"/>
    <section class="page-wrapper container">
        <#nested/>
    </section>
    <#include "footer.ftl"/>
    </body>



    </html>
</#macro>