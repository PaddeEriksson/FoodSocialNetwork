var app = angular.module('app', ['angularFileUpload']);

app.controller('AppController', function($scope, FileUploader) {
        var tempUploader = new FileUploader();
        tempUploader.url = "http://localhost:9000/uploaded";
        tempUploader.formData = {fileName:"pelle"};

        $scope.uploader = tempUploader;

    });