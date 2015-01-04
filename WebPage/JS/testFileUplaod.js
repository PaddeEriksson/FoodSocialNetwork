var app = angular.module('app', ['angularFileUpload']);

app.controller('AppController', function($scope, FileUploader) {
        var tempUploader = new FileUploader();
        tempUploader.url = "http://83.254.221.239:9000/uploaded";
        tempUploader.formData = {fileName:"pelle"};

        $scope.uploader = tempUploader;

    });