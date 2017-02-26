
create database if not exists library;

use library;

create table if not exists `library_books`(
	`id` int primary key auto_increment,
	`bookname` varchar(150),
	`status` varchar(150),
	`issuefrom` varchar(150),
	`issuedto` varchar(150),
	`updated_at` datetime not null default CURRENT_TIMESTAMP;
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


create database if not exists login;

use login;

create table if not exists `login_details`(
	`id` int primary key auto_increment,
	`username`  varchar(150),  
	`password`  varchar(150),   
	`firstname` varchar(150),  
	`lastname`  varchar(150),    
	`usertype`  varchar(150),
	`email` varchar(150);
)


insert into login_details (`username`,`password`,`firstname`,`lastname`,`usertype`,`email`) values('sumanth','sumanth','sumanth','reddy','admin','sumanth448@yahoo.com');

insert into login_details (`username`,`password`,`firstname`,`lastname`,`usertype`,`email`) values('testuser','testuser','testuser','testuser','librarian','testuser@yahoo.com');