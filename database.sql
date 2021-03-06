﻿create database ManagerLibrary
go
use ManagerLibrary
go
create table category(
	cat_id int identity primary key,
	name nvarchar(100) not null,
	status bit default(0)
)
go
create table account(
	account_id int identity primary key,
	username varchar(50) not null,
	password varchar(255) not null,
	decentralization int check(decentralization >= 0),
	status bit default(0)
)
go
create table employee(
	employee_id int identity primary key,
	account_id int foreign key references account(account_id) unique,
	name nvarchar(100) not null,
	dateofbirth date not null,
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
	date_create date default GETDATE(),
	quantity int default(0) not null check(quantity >= 0),
	price float default(0) not null check(price >= 0),
	status tinyint default(0)
)
go
create table student(
	id int identity primary key,
	student_id varchar(10) not null unique,
	name nvarchar(100) not null,
	gender bit default(0),
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
	date_borrow datetime default GETDATE() not null,
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
go 

create proc sp_GetAllCategory
as
	select * from category where status = 0
go

create proc sp_GetCategory
@cat_id int
as
	select * from category where cat_id like @cat_id AND status = 0
go

create proc sp_SearchByNameCategory
@name nvarchar(100)
as
	select * from category where name like @name AND status = 0
go

create proc sp_CreateCategory
@name nvarchar(100),
@error nvarchar(255) output
as
	if(exists(select * from category where name = @name))
		begin
			set @error = N'Tên đã tồn tại-'
			return
		end
	insert into category(name) values (@name)
go

create proc sp_UpdateCategory
@cat_id int,
@name nvarchar(100),
@error nvarchar(100) output
as 
	DECLARE @flag INT
	set @flag = 0

	if( NOT exists(select * from category where cat_id = @cat_id AND status = 0))
		begin
			set @error = N'Mã loại sản phẩm không tồn tại-'
			set @flag = 1
		end
	if(exists(select * from category where name = @name AND status = 0))
		begin
			set @error += N'Tên loại sản phẩm đã tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			update category set name = @name where cat_id = @cat_id
			set @error = ''
		end
go

create proc sp_DeleteCategory
@cat_id int,
@error nvarchar(100) output
as
	if(exists(select * from category where cat_id = @cat_id and status = 0))
		begin
			update category set status = 1 where cat_id = @cat_id
			set @error =''
			return
		end
	set @error = N'Mã loại sản phẩm không tồn tại-'
go

/*	account
	account_id int identity primary key,
	username varchar(50) not null unique,
	password varchar(255) not null,
	decentralization int check(decentralization >= 0),
	status bit default(0)
*/
create proc sp_GetAllAccount
@account_id int 
as
	select * from account where account_id = @account_id
go

create proc sp_SearchAccount
@username varchar(50)
as
	select * from account where username = @username AND status = 0
go

create proc sp_Login
@username varchar(50),
@password varchar(255)
as
	select * from account where username = @username AND password = @password AND status = 0
go

create proc sp_UpdateAccount
@account_id int,
@password varchar(255),
@error nvarchar(255) output
as
	
	if(NOT exists(select * from account where account_id = @account_id and status = 0))
		begin
			set @error = 'không tồn tại tài khoản-'
		end
	else
		begin
			update account set password = @password where account_id = @account_id
			set @error = ''
		end
go

create proc sp_CreateAccount
@username varchar(50),
@password varchar(255),
@decentralization int,
@error nvarchar(255) output
as
	if(exists(select * from account where username = @username))
		begin
			set @error =  N'Tên đăng nhập đã tồn tại-'
			return
		end
	insert into account values(@username,@password,@decentralization,0)
	set @error = ''
go

create proc sp_DeleteAccount
@account_id int,
@error nvarchar(255) output
as
	if(exists(select * from account where account_id = @account_id AND status = 0))
		begin
			set @error = N'ID không tồn tại'
			return
		end
	update account set status = 1 where account_id = @account_id
	set @error =''
go

/* employee
	employee_id int identity primary key,
	account_id int foreign key references account(account_id),
	name nvarchar(100) not null,
	dateofbirth date not null,
	phone varchar(10) not null unique,
	email varchar(50) not null unique,
	home_town nvarchar(255) not null,
	status bit default(0)
*/

create proc sp_GetAllEmployee
as
	select * from employee where status = 0
go

create proc sp_GetEmployee
@employee_id int
as
	select * from employee where employee_id = @employee_id AND status = 0
go

create proc sp_FindByPhoneEmployee
@phone varchar(10)
as
	select * from employee where phone like @phone
go

create proc sp_FindByEmailEmployee
@email varchar(50)
as
	select * from employee where email like @email
go

create proc sp_GetByAccountEmployee
@account_id int,
@error nvarchar(255) output
as
	
	if(exists(select * from employee where account_id = account_id AND status = 0))
		begin
			select * from employee where account_id = account_id AND status = 0
			set @error = ''
			return
		end
	set @error = N'Không tồn tại tài khoản'
go

create proc sp_CreateEmployee
@account_id int,
@name nvarchar(100),
@dateofbirth date,
@phone varchar(10),
@email varchar(50),
@home_town nvarchar(255),
@error nvarchar(255) output
as
	DECLARE @flag int
	set @flag = 0
	if(NOT exists(select * from account where account_id = @account_id))
		begin
			set @error = N'Mã tài khoản không tồn tại-'
		end
	if(exists(select * from employee where phone = @phone))
		begin
			set @error = N'Số điện thoại đã tồn tại-'
			set @flag = 1
		end
	if(exists(select * from employee where email = @email))
		begin
			set @error += N'Email đã tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			insert into employee(name , dateofbirth , phone , email, home_town) values(@name , @dateofbirth, @phone , @email , @home_town)
			set @error = ''
			return
		end
go

create proc sp_UpdateEmployee
@employee_id int,
@account_id int,
@name nvarchar(100),
@dateofbirth date,
@phone varchar(10),
@email varchar(50),
@home_town nvarchar(255),
@error nvarchar(255) output
as
	DECLARE @flag int
	set @flag = 0
	if(NOT exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			set @error = N'Mã nhân viên không tồn tại-'
			set @flag = 1
		end
	if(NOT exists(select * from employee where account_id = @account_id AND status = 0))
		begin
			set @error = N'Tài khoản không tồn tại-'
			set @flag = 1
		end
	if(exists(select * from employee where phone = @phone))
		begin
			set @error = N'Số điện thoại đã tồn tại-'
			set @flag = 1
		end
	if(exists(select * from employee where email = @email))
		begin
			set @error += N'Email đã tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			update employee set name = @name , dateofbirth = @dateofbirth, phone = @phone , email = @email , home_town = @home_town where employee_id = @employee_id
			set @error = ''
			return
		end
go

create proc sp_DeleteEmployee
@account_id int,
@error nvarchar(255) output
as
	if(exists(select * from employee where account_id = @account_id AND status = 0))
		begin 
			update employee set status = 0 where account_id = @account_id
			set @error = ''
		end
	set @error = N'Không tồn tại nhân viên-'
go

/*Book
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
*/

create proc sp_GetAllBook
as
	select * from book
go

create proc sp_GetBook
@book_id int
as
	select * from book where book_id = @book_id AND status = 0
go

create proc sp_CreateBook
@name nvarchar(100),
@cat_id int,
@author nvarchar(100),
@description ntext,
@publication_date date,
@quantity int,
@price float,
@error nvarchar(255) output
as
	if(exists(select * from category where cat_id = @cat_id))
		begin
			insert into book (name , cat_id , author , description , publication_date , quantity , price) values (@name , @cat_id , @author , @description , @publication_date , @quantity,@price)
			set @error = ''
			return 
		end
	else
		begin
			set @error = N'không tồn tại mã sản phảm-'
			return
		end
go

create proc sp_UpdateBook
@book_id int,
@name nvarchar(100),
@cat_id int,
@author nvarchar(100),
@description ntext,
@publication_date date,
@quantity int,
@price float,
@error nvarchar(255) output
as
	if(exists(select * from book where book_id = @book_id))
		begin
			if(exists(select * from category where cat_id = @cat_id))
				begin
					update book set name = @name , cat_id = @cat_id , author = @author , description = @description , publication_date = @publication_date ,quantity = @quantity,price = @price where book_id =@book_id
					set @error = ''
					return
				end
			else
				begin
					set @error = N'Không tồn tại mã loại sách-'
					return
				end
		end
	else
		begin
			set @error += N'Mã sách không tồn tại-'
			return
		end
go

create proc sp_DeleteBook
@book_id int,
@error nvarchar(255) output
as
	if(exists(select * from book where book_id = @book_id AND status = 0))
		begin
			update book set status = 1 where book_id = @book_id
			set @error = ''
			return
		end
	set @error = N'Sách không tồn tại-'
go

/* Student
	id int identity primary key,
	student_id varchar(10) not null unique,
	name nvarchar(100) not null,
	gender bit default(0)
	class_name varchar(100) not null,
	phone varchar(10) not null unique,
	dateofbirth date not null,
	status tinyint default(0),
*/

create proc sp_GetAllStudent
as
	select * from student where status = 0
go

create proc sp_GetStudent 
@id int
as
	select * from student where id = @id and status = 0
go

create proc sp_GetStudentByStudentId
@student_id varchar(10)
as
	select * from student where student_id like @student_id and status = 0
go

create proc sp_CreateStudent
@student_id varchar(10),
@name nvarchar(100),
@gender bit,
@class_name varchar(100),
@phone varchar(10),
@dateofbirth date,
@error nvarchar(255) output
as
	DECLARE @flag int
	set @flag = 0
	
	if(exists (select * from student where student_id = @student_id and status = 0))
		begin
			set @error=N'Mã sinh viên đã tồn tại-'
			set @flag=1
		end
	if(exists (select * from student where phone = @phone))
		begin
			set @error = N'Số điện thoại đã tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			insert into student(student_id , name , gender, class_name , phone , dateofbirth ) values (@student_id , @name , @gender , @class_name , @phone , @dateofbirth)
			set @error = ''
		end
go

create proc sp_UpdateStudent
@id int,
@student_id varchar(10),
@name nvarchar(100),
@gender bit,
@class_name varchar(100),
@phone varchar(10),
@dateofbirth date,
@error nvarchar(255) output
as
	DECLARE @flag int
	set @flag = 0
	if(NOT exists (select * from student where is = @id and status = 0))
		begin
			set @error = N'Mã không tồn tại-'
			set @flag = 1
		end
	if(exists (select * from student where student_id = @student_id and status = 0))
		begin
			set @error=N'Mã sinh viên đã tồn tại-'
			set @flag=1
		end
	if(exists (select * from student where phone = @phone))
		begin
			set @error = N'Số điện thoại đã tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			update student set student_id = @student_id , name = @name, gender = @gender , class_name = @class_name , phone = @phone,dateofbirth = @dateofbirth where id = @id
			set @error = ''
			return
		end
go

create proc sp_DeleteStudent
@id int,
@error nvarchar(255) output
as
	if(exists(select * from student where id = @id AND status = 0))
		begin
			update student set status = 1 where id = @id
			set @error = ''
			return
		end
	set @error = N'xóa không thành công'
go

/*
	borrow_id int identity primary key,
	student_id int foreign key references student(id),
	employee_id int foreign key references employee(employee_id),
	date_borrow datetime default GETDATE() not null,
	status bit default(0)
*/

create proc sp_GetAllBorrow
as
	select * from borrow where status = 0
go

create proc sp_GetBorrow 
@borrow_id int
as
	select * from borrow where borrow_id = @borrow_id AND status = 0
go

create proc sp_createBorrow
@student_id int,
@employee_id int,
@error nvarchar(255) output
as
	DECLARE @flag INT
	set @flag = 0;
	if(not exists(select * from student where id = @student_id AND status = 0))
		begin
			set @error = N'Mã sinh viên không tồn tại-'
			set @flag = 1
		end
	if(not exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			set @error = N'Mã nhân viên không tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin 
			insert into borrow(student_id , employee_id) values(@student_id , @employee_id)
			set @error = ''
			return
		end
go

create proc sp_UpdateBorrow
@borrow_id int,
@student_id int,
@employee_id int,
@error nvarchar(255) output
as
	DECLARE @flag INT
	set @flag = 0;

	if(not exists(select * from borrow where borrow_id = @borrow_id))
		begin
			set @error += N'Mã đơn mượn không tồn tại-'
			set @flag = 1
		end

	if(not exists(select * from student where id = @student_id AND status = 0))
		begin
			set @error += N'Mã sinh viên không tồn tại-'
			set @flag = 1
		end

	if(not exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			set @error += N'Mã nhân viên không tồn tại-'
			set @flag = 1
		end

	if(@flag = 0)
		begin 
			update borrow set student_id = @student_id , employee_id = employee_id where borrow_id = @borrow_id
			set @error = ''
			return
		end
go

create proc sp_DeleteBorrow
@borrow_id int,
@error nvarchar(255) output
as
	if(exists(select * from borrow where borrow_id = @borrow_id  AND status = 0))
		begin
			update borrow set status = 1 where borrow_id = @borrow_id
			set	@error = ''
			return
		end
	set @error = N'Không tôn tại đơn mượn-'
go

/*Borrow_detail
	borrow_detail_id int identity primary key,
	borrow_id int foreign key references borrow(borrow_id),
	book_id int foreign key references book(book_id),
	date_appointment date not null,
	date_return datetime,
	status bit default(0)
*/

create proc sp_GetAllBorrowDetail
as
	select * from borrow_detail where status = 0
go

create proc sp_GetBorrowDetail
@borrow_detail_id int
as
	select * from borrow_detail where borrow_detail_id = @borrow_detail_id AND status = 0;
go

create proc sp_CreateBorrowDetail
@borrow_id int,
@book_id int,
@date_appointment date,
--@date_return datetime,
--@status bit,
@error nvarchar(255) output
as
	DECLARE	@flag INT
	set @flag = 0;

	if(NOT exists(select * from borrow where borrow_id = @borrow_id AND status = 0))
		begin
			set @error += N'Mã đơn mượn không tồn tại-'
			set @flag = 1
		end
	if(NOT exists(select * from book where book_id = @book_id AND status = 0))
		begin
			set @error += N'Mã sách không tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			insert into borrow_detail(borrow_id , book_id , date_appointment ) values(@borrow_id , @book_id , @date_appointment)
			set @error = ''
			return
		end
go

create proc sp_UpdateBorrowDetail
@borrow_detail_id int,
@book_id int,
@date_appointment date,
@date_return datetime,
@error nvarchar(255) output
as
	DECLARE @flag INT
	set @flag = 0
	if(NOT exists(select * from borrow_detail where borrow_detail_id = @borrow_detail_id AND status = 0))
		begin
			set @error = N'Không tồn tại mã đơn mượn chi tiết-' 
			set @flag = 1
		end
	if(NOT exists(select * from book where book_id = @book_id AND status = 0))
		begin
			set @error += N'Không tồn tại mã sách-'
			set @flag = 1 
		end
	if(@flag = 0)
		begin
			update borrow_detail set book_id = @book_id, @date_appointment = @date_appointment , date_return = @date_return where borrow_detail_id = @borrow_detail_id
			set @error = ''
			return
		end
go

create proc sp_DeleteBorrowDetail
@borrow_detail_id int,
@error nvarchar(255) output
as
	if(exists(select * from borrow_detail where borrow_detail_id = @borrow_detail_id))
		begin
			update borrow_detail set status = 1 where borrow_detail_id = @borrow_detail_id
			set @error = ''
			return
		end
	set @error = N'id không tồn tại-'
go
/*punish 
	punish_id int identity primary key,
	student_id int foreign key references student(id),
	money float check(money > 0),
	reason ntext not null,
	employee_id int foreign key references employee(employee_id),
	borrow_detail_id int foreign key references borrow_detail(borrow_detail_id),
	status bit default(0),
*/
 create proc sp_GetAllPunish
 as
	select * from punish
go

create proc sp_GetPunish
@punish_id int,
@error nvarchar(255)
as
	select * from punish where punish_id = @punish_id
go

create proc sp_CreatePunish
@student_id int,
@money float,
@reason ntext,
@employee_id int,
@borrow_detail_id int,
@error nvarchar(255)
as
	DECLARE @flag int
	set @flag = 0
	
	if(Not exists(select * from student where id = @student_id AND status = 0))
		begin
			set @error = N'Mã Sinh viên không tồn tại-'
			set @flag = 1
		end
	if(Not exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			set @error = N'Mã nhân viên không tồn tại-' 
			set @flag = 1
		end
	if(NOT exists(select * from borrow_detail where borrow_detail_id = @borrow_detail_id))
		begin
			set @error = N'Mã đơn mượn chi tiết không tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			insert into punish(student_id , money , reason , employee_id , borrow_detail_id) values(@student_id , @money , @reason , @employee_id, @borrow_detail_id)
			set @error = ''
			return
		end
go

create proc sp_UpdatePunish
@punish_id int,
@student_id int,
@money float,
@reason ntext,
@employee_id int,
@borrow_detail_id int,
@error nvarchar(255)
as	
	DECLARE @flag int
	set @flag = 0
	
	if(NOT exists(select * from punish where punish_id = @punish_id))
		begin
			set @error = N'Mã phạt không đúng-'
			set @flag = 1
		end
	if(Not exists(select * from student where id = @student_id AND status = 0))
		begin
			set @error = N'Mã Sinh viên không tồn tại-'
			set @flag = 1
		end
	if(Not exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			set @error = N'Mã nhân viên không tồn tại-' 
			set @flag = 1
		end
	if(NOT exists(select * from borrow_detail where borrow_detail_id = @borrow_detail_id))
		begin
			set @error = N'Mã đơn mượn chi tiết không tồn tại-'
			set @flag = 1
		end
	if(@flag = 0)
		begin
			update punish set student_id = @student_id , money = @money , reason = @reason , employee_id = @employee_id ,borrow_detail_id = @borrow_detail_id where punish_id = @punish_id
			set @error = ''
			return
		end
go

create proc sp_DeletePunish
@punish_id int,
@error nvarchar(255) output
as
	if(exists(select * from punish where punish_id = @punish_id))
		begin
			update punish set status = 1 where punish_id = @punish_id
			set @error = ''
			return
		end
	set @error = N'Mã phạt không tồn tại'

