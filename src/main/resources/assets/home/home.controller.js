(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', 'FlashService'];
    function HomeController(UserService, $rootScope,FlashService) {
    //start of scope
           var vm = this;
           vm.UpdateBookDetails=UpdateBookDetails;
           vm.deletebook=deletebook;
           vm.addnewbook=addnewbook;
           vm.addnewbookdetails = {};
           vm.bookname=null;
           vm.user = null;
           vm.usertype = null;
           vm.editingData = {};
           vm.allUsers = [];
           vm.emaildetails = [];
           vm.inserted = {};
           initController();
           vm.predicate = 'index';
           vm.reverse = false;
           vm.showModal = false;
           vm.currentPage = 1;
           vm.currentUser = null;
           vm.selecteduserforissue={email : 'Pick the Email'};
           vm.stateSelected = function (state) {
               console.log(state);
               vm.stateInfo = state;
           }
           vm.order = function (predicate) {
             console.log("entered predicate");
             vm.reverse = (vm.predicate === predicate) ? !vm.reverse : false;
             vm.predicate = predicate;
           };
           vm.open = function(user) {
             console.log("entered open");
             vm.currentUser = user;
             console.log(vm.currentUser);
             vm.showModal = true;
           };
           vm.issuebook = function(book){
              console.log("enetedd issuebook" + book);
           };
           vm.ok = function() {
             vm.showModal = false;
           };

           vm.cancel = function() {
             vm.showModal = false;
           };
           vm.modify = function(tableData){
                   console.log(tableData.book);
                   vm.editingData[tableData.id] = true;
           };
           /*vm.update = function(tableData){
                   console.log(tableData);
                   tableData.issuedfrom = vm.user.email;
                   console.log(tableData);
                   var res = UpdateBookDetails(tableData);
                   vm.editingData[tableData.id] = false;
               };*/
            vm.remove = function(tableData){
                  for(var i=0;i<vm.allUsers.length;i++){
                     if(vm.allUsers[i].book == tableData.book){
                         vm.allUsers.splice(i,1);
                         break;
                     }
                  }
               };
           for(var i=0;i<vm.allUsers.length;i++){
               vm.editingData[vm.allUsers.id] = false;
           }
           vm.status = [
             { status: 'free' },
             { status: 'issued'}
           ];
           vm.statusvalue = vm.status[0];
           vm.totalItems = vm.allUsers.length;
           console.log(vm.totalItems);
           vm.numPerPage = 50;
           vm.paginate = function(value) {
             var begin, end, index;
             begin = (vm.currentPage - 1) * vm.numPerPage;
             end = begin + vm.numPerPage;
             index = vm.allUsers.indexOf(value);
             return (begin <= index && index < end);
           };

           //end of scope

        function initController() {
            loadCurrentUser();
            loadAllUsers();
            GetEmailDetails();
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    console.log(user);
                    vm.user = user;
                    vm.usertype = user.usertype;
                    console.log(vm.usertype);
                });
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function GetEmailDetails() {
                    UserService.GetEmailDetails()
                        .then(function (users) {
                            vm.emaildetails = users;
                        });
                }
        function UpdateBookDetails(bookdetails) {
           bookdetails.issuedfrom = vm.user.email;
                            UserService.UpdateBookDetails(bookdetails)
                                .then(function (users) {
                                    if(users.success){
                                     FlashService.Success(users.message, true);
                                     vm.editingData[bookdetails.id] = false;
                                    }else{
                                      FlashService.Error(users.message);
                                      vm.editingData[bookdetails.id] = true;
                                    }
                                });

                        }

        function deletebook(bookdetails) {
                     UserService.DeleteBook(bookdetails)
                     .then(function (users) {
                       if(users.success){
                       vm.remove(bookdetails);
                       FlashService.Success(users.message, true);
                       }else{
                       FlashService.Error(users.message);
                       }
                     });
        }
        function addnewbook(statusvalue,selecteduserforissue,bookname) {
                             console.log(selecteduserforissue);
                             vm.addnewbookdetails.status= statusvalue;
                             vm.addnewbookdetails.issuedto=selecteduserforissue;
                             vm.addnewbookdetails.issuefrom=vm.user.email;
                             vm.addnewbookdetails.bookname=bookname;
                             UserService.AddBook(vm.addnewbookdetails)
                             .then(function (users) {
                               if(users.success){
                               loadAllUsers();
                               FlashService.Success(users.message, true);
                               }else{
                               FlashService.Error(users.message);
                               }
                             });
                }
    }

})();