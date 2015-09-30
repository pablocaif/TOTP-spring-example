/**
 * Created by pablocaif on 29/09/15.
 */

var module = angular.module("spring-totp", ['ngRoute', 'ngResource']).
    controller("UserAdminController", ['$scope','$http', function ($scope, $http) {
        var userAdmin = this;
        userAdmin.user = {};
        userAdmin.username = '';
        userAdmin.userExists = false;
        userAdmin.createUser = function () {
            var userCreated = {
                username: userAdmin.user.username,
                password: userAdmin.user.password,
                enabled: true
            };

            $http.post("/rest/user", userCreated).success(function (result) {
                userAdmin.username = result.username;
                userAdmin.userExists = true;
            });


        };
        userAdmin.showQRCode = function () {
            return "/rest/qrcode/" + userAdmin.username + ".png"
        };

    }]);