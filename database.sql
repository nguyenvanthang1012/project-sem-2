create database ManagerLibrary
go
use ManagerLibrary
go
create table category(
	cat_id int identity primary key,
	name nvarchar(100) not null,
	status bit default(1)
)
go
create table book(
	book_id int identity primary key,
	name nvarchar(100) not null,
	cat_id int foreign key references category(cat_id),
	author nvarchar(100) default(N'no name'),
	description text not null,
	publication_date date check(publication_date < GETDATE()),
	quantity int default(0) not null check(quantity >= 0),
	price int default(0) not null check(price >= 0),
	status tinyint default(0),
	note text,
)
go
create table student(
	student_id int identity primary key,
	name nvarchar(100) not null,
	phone varchar(10) not null unique,
	email varchar(100) not null unique,
	dateofbirth date not null,
	home_town nvarchar(255) not null,
	status tinyint default(0),
)
go
create table employee(
	employee_id int identity primary key,
	identity_card varchar(12) not null unique,
	name nvarchar(100) not null,
	phone varchar(10) not null unique,
	email varchar(50) not null unique,
	home_town nvarchar(255) not null
)
go
create table borrow(
	borrow_id int identity primary key,
	student_id int foreign key references student(student_id),
	employee_id int foreign key references employee(employee_id),
	date_borrow date  not null,
)
go
create table borrow_detail(
	borrow_detail_id int identity primary key,
	borrow_id int foreign key references borrow(borrow_id),
	book_id int foreign key references book(book_id),
	date_return date,
)
go
create table punish(
	punish_id int identity primary key,
	student_id int foreign key references student(student_id),
	money int check(money > 0),
	reason text not null,
	employee_id int foreign key references employee(employee_id)
)