/**
 * webdemo Module
 *
 * Description
 */
angular.module('webdemo')
    .controller('mainCtrl', function($scope, $http) {
        $scope.showOutput = function(input, jtex) {
            $scope.output = input + jtex
        }
    })
