create database ManagerLibrary
go
use ManagerLibrary
go
create table category(
	cat_id int identity primary key,
	name nvarchar(100) not null,
	status bit default(0)
)
go
create table accout(
	id_accout int identity primary key,
	username varchar(50) not null unique,
	password varchar(255) not null,
	decentralization int check(decentralization >= 0)
)
go
create table employee(
	employee_id int identity primary key,
	id_accout int foreign key references accout(id_accout),
	identity_card varchar(12) not null unique,
	name nvarchar(100) not null,
	phone varchar(10) not null unique,
	email varchar(50) not null unique,
	home_town nvarchar(255) not null,
	status bit default(0)
)
go
create table book(
	book_id int identity primary key,
	name nvarchar(100) not null,
	cat_id int foreign key references category(cat_id),
	author nvarchar(100) default('no name'),
	description ntext not null,
	publication_date date check(publication_date < GETDATE()),
	date_create datetime,
	quantity int default(0) not null check(quantity >= 0),
	price float default(0) not null check(price >= 0),
	status tinyint default(0)
)
go
create table student(
	id int identity primary key,
	student_id varchar(10) not null unique,
	name nvarchar(100) not null,
	class_name varchar(100) not null,
	phone varchar(10) not null unique,
	dateofbirth date not null,
	status tinyint default(0),
)
go

create table borrow(
	borrow_id int identity primary key,
	student_id int foreign key references student(id),
	employee_id int foreign key references employee(employee_id),
	date_borrow datetime  not null,
	status bit default(0)
)
go
create table borrow_detail(
	borrow_detail_id int identity primary key,
	borrow_id int foreign key references borrow(borrow_id),
	book_id int foreign key references book(book_id),
	date_appointment date not null,
	date_return datetime,
	status bit default(0)
)
go
create table punish(
	punish_id int identity primary key,
	student_id int foreign key references student(id),
	money float check(money > 0),
	reason ntext not null,
	employee_id int foreign key references employee(employee_id),
	borrow_detail_id int foreign key references borrow_detail(borrow_detail_id),
	status bit default(0),
)