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
create table account(
	account_id int identity primary key,
	username varchar(50) not null unique,
	password varchar(255) not null,
	decentralization int check(decentralization >= 0),
	status bit default(0)
)
go
create table employee(
	employee_id int identity primary key,
	account_id int foreign key references account(account_id) unique,
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
	date_return datetime not null check(date_return > date_borrow),
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
	select * from category
go

create proc sp_SearchCategory
@cat_id int
as
	select * from category where cat_id like @cat_id
go

create proc sp_SearchByNameCategory
@name nvarchar(100)
as
	select * from category where name like @name
go

create proc sp_AddCategory
@name nvarchar(100)
as
	insert into category(name) values (@name)
go

create proc sp_UpdateCategory
@cat_id int,
@name nvarchar(100) not null,
@status bit,
@error nvarchar(100) output
as 
	if(exists(select * from category where cat_id = @cat_id))
		begin
			update category set name = @name , status = @status where cat_id = @cat_id
			set @error = ''
			return
		end
	set @error=N'Mã loại sản phẩm không tồn tại'
go

create proc sp_DeleteCategory
@cat_id int,
@error nvarchar(100) output
as
	if(exists(select * from category where cat_id = @cat_id))
		begin
			delete from category where cat_id = @cat_id
			set @error =''
			return
		end
	set @error = N'Mã loại sản phẩm không tồn tại'
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
@status bit,
@error nvarchar(255) output
as
	if(exists(select * from account where account_id = @account_id))
		begin
			if((select status from account where account_id = @account_id) = 1 AND @status = 0)
				begin
					set @error = N'Cập nhật không thành công'
				end
			else
				begin
					update account set password = @password , status = @status where account_id = @account_id
					set @error = N''
				end
		end
	else
		begin
			set @error = N'Không tồn tại id'
		end
go

create proc sp_InseartAccount
@username varchar(50),
@password varchar(255),
@decentralization int,
@error nvarchar(255) output
as
	if(exists(select * from account where username = @username))
		begin
			set @error =  N'Tên đăng nhập đã tồn tại'
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
	phone varchar(10) not null unique,
	email varchar(50) not null unique,
	home_town nvarchar(255) not null,
	status bit default(0)
*/

create proc sp_GetAllEmployee
as
	select * from employee where status = 0
go

create proc sp_FindByPhone
@phone varchar(10)
as
	select * from employee where phone like @phone
go

create proc sp_FindByEmail
@email varchar(50)
as
	select * from employee where email like @email
go

create proc sp_GetEmployee
@employee_id int,
@error nvarchar(255) output
as
	if(exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			select * from employee where employee_id = @employee_id AND status = 0
			set @error = ''
			return
		end
	set @error = N'Không tồn tại nhân viên có ID '+@employee_id
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
	set @error = N'Không tồn tại thông tin nhân viên'
go

create proc sp_UpdateEmployee
@account_id int,
@name nvarchar(100),
@phone varchar(10),
@email varchar(50),
@home_town nvarchar(255),
@status bit,
@error nvarchar(255) output
as
	if(exists(select * from employee where account_id = @account_id AND status = 0 ))
		begin
			update employee set name = @name , phone = @phone , email = @email , home_town = @home_town where account_id = @account_id
			set @error = ''
			return
		end
	set @error = N'không tồn tại nhân viên'
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
	set @error = N'Không tồn tại nhân viên'
go

create proc sp_CreateEmployee
@name nvarchar(100),
@phone varchar(10),
@email varchar(50),
@home_town nvarchar(255),
@error nvarchar(255) output
as
	if(exists(select * from employee where phone = @phone))
		begin
			set @error = N'Số điện thoại đã tồn tại'
			return
		end
	else
		begin
			if(exists(select * from employee where email = @email))
				begin
					set @error = N'Email đã tồn tại'
					return
				end
			else
				begin
					insert into employee(name , phone , email, home_town) values(@name , @phone , @email , @home_town)
					set @error = ''
					return
				end
		end
	set @error = N'101'
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
@book_id int,
@error nvarchar(255) output
as
	if(exists(select * from book where book_id = @book_id))
		begin
			select * from book where book_id = @book_id
			set @error = ''
			return
		end
	set @error = N'Không tồn tại mã sách'
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
			set @error = N'không tồn tại mã sản phảm'
			return
		end
	set @error = N'101'
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
					set @error = N'Không tồn tại mã loại sách'
					return
				end
		end
	else
		begin
			set @error = N'Mã sách không tồn tại'
			return
		end
	set @error = '101'
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
	set @error = N'Sách không tồn tại'
go

/*
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
@id int,
@error nvarchar(255) output
as
	if(exists(select * from student where id = @id))
		begin
			select * from student where id = @id
			set @error = ''
			return
		end
	set @error = N'Không tồn tại sinh viên'
go

create proc sp_GetStudentByStudentId
@student_id varchar(10),
@error nvarchar(255) output
as
	if(exists(select * from student where student_id = @student_id))
		begin
			select * from student where student_id = @student_id
			set @error = ''
			return
		end
	set @error = N'Không tồn tại mã sinh viên ' + @student_id
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
	if(exists(select * from student where student_id = @student_id OR phone = @phone))
		begin
			set @error = N'Thông tin sinh viên đã trùng'
			return
		end
	insert into student(student_id , name , gender, class_name , phone , dateofbirth ) values (@student_id , @name , @gender , @class_name , @phone , @dateofbirth)
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
	if(exists(select * from student where id = @id))
		begin
			update student set student_id = @student_id , name = @name, gender = @gender , class_name = @class_name , phone = @phone,dateofbirth = @dateofbirth where id = @id
			set @error = N''
			return
		end
	set @error = N'Không tồn tại sinh viên'
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
	date_return datetime not null check(date_return > date_borrow),
	status bit default(0)
*/

create proc sp_GetAllBorrow
as
	select * from borrow where status = 0
go

create proc sp_GetBorrow 
@borrow_id int,
@error nvarchar(255) output
as
	if(exists(select * from borrow where borrow_id = @borrow_id))
		begin
			select * from borrow where borrow_id = @borrow_id
			set @error = ''
			return
		end
	set @error = 'Không tồn tại đơn mượn'
go

create proc sp_createBorrow
@student_id int,
@employee_id int,
@date_return datetime,
@error nvarchar(255) output
as
	if(exists(select * from student where id = @student_id AND status = 0) AND @date_return > GETDATE() AND exists(select * from employee where employee_id = @employee_id AND status = 0))
		begin
			insert into borrow(student_id , employee_id , date_return) values(@student_id , @employee_id , @date_return)
			set @error = ''
			return
		end
	set @error = N'Thông tin không phù hợp'
go

create proc sp_UpdateBorrow
@borrow_id int,
@student_id int,
@employee_id int,
@date_return datetime,
@error nvarchar(255) output
as
	if(exists(select * from borrow where borrow_id = @borrow_id))
		begin
			update borrow set student_id = @student_id , employee_id = employee_id , date_return = @date_return where borrow_id = @borrow_id
			set @error = ''
			return
		end
	set @error = N'Cập nhật không thành công'
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
	set @error = N'Xóa không thành công'
go
	


