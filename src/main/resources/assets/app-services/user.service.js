(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.GetEmailDetails = GetEmailDetails;
        service.UpdateBookDetails = UpdateBookDetails;
        service.DeleteBook = DeleteBook;
        service.AddBook = AddBook;

        return service;

        function GetAll() {
            return $http.get('/api/home').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            console.log("entered user service");
            return $http.get('/api/login/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            console.log("entered create user");
            return $http.post('/api/register', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        function GetEmailDetails() {
            return $http.get('/api/home/getemaildetails').then(handleSuccess, handleError('Error getting email details'));
        }UpdateBookDetails

        function UpdateBookDetails(bookdetails) {
            return $http.post('/api/home/updatebookdetails',bookdetails).then(handleSuccess, handleError('Error updating details'));
        }

        function DeleteBook(bookdetails) {
                    return $http.post('/api/home/deletebook',bookdetails).then(handleSuccess, handleError('Error deleting books'));
                }

        function AddBook(bookdetails) {
                     return $http.post('/api/home/addbook',bookdetails).then(handleSuccess, handleError('Error adding books'));
                 }

        // private functions

        function handleSuccess(res) {
            console.log("entered handle sucess");
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
