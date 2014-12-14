var app = angular.module('app', ['angularFileUpload']);


app.controller('AppController', function($scope, FileUploader) {
        var tempUploader = new FileUploader();
        tempUploader.url = "http://whatever";

        $scope.uploader = tempUploader;

    });