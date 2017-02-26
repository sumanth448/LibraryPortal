(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope'];
    function HomeController(UserService, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.allUsers = [];
        vm.deleteUser = deleteUser;
        vm.inserted = {};

        initController();

        function initController() {
            loadCurrentUser();
            loadAllUsers();
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    console.log(user);
                    vm.user = user;
                });
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }


/*
        $scope.saveUser = function(data, id) {
                //$scope.user not updated yet
                angular.extend(data, {
                    key: id
                });
                //return $http.post('/saveUser', data);
            };

            // remove user
            $scope.removeUser = function(index) {
                $scope.users.splice(index, 1);
            };

            //remove all users
             $scope.deleteUser = function() {
                $scope.users.splice(0,$scope.users.length);
            };

            // add user
            $scope.addUser = function() {
                $scope.inserted = {
                    key: $scope.users.length + 1,
                    value: ''
                };
                $scope.users.push($scope.inserted);
                    };*/
        function addBook(book){
              vm.inserted = {
                  index:vm.allUsers.length + 1,
                  book:book
              }
        }
    }

})();