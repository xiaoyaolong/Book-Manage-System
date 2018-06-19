use Book_Manage;
/*drop table dbo.books;
create table books
(
book_id char(13) primary key,
book_name nvarchar(50) not null,
book_author nvarchar(20),
book_date date,
book_from nvarchar(20) not null,
book_loc nvarchar(5) check (book_loc in ('鲍家汇', '望府', '长丰')) default '望府' not null,
book_cal nvarchar(5) check (book_cal in ('文学','数学','计算机','通信电子','金融经济','其他')) default '其他'
)

insert into books values (9787115418081,'Oracle 数据库管理与开发','尚展垒','20160401','人民邮电出版社','鲍家汇','计算机')
insert into books values (9787309120042,'银行业法律法规与综合能力','银行业专业人员职业资格考试研究中心','20160101','复旦大学出版社','望府','金融经济')
insert into books values (1,'a','aa','20160401','人民邮电出版社','鲍家汇','数学')
insert into books values (2,'b','bb','20160101','复旦大学出版社','望府','金融经济')
insert into books values (3,'c','cc','20160401','人民邮电出版社','鲍家汇','数学')
insert into books values (4,'d','dd','20160101','复旦大学出版社','望府','金融经济')
insert into books values (5,'e','ee','20160401','人民邮电出版社','鲍家汇','数学')
insert into books values (6,'f','ff','20160101','复旦大学出版社','望府','金融经济')*/

select book_id ISBN, book_name 图书名称, book_author 作者, book_loc 所在位置, book_cal 图书类别 from books;

select top 1 * from books where (book_id > (select max(book_id) from (select top 3 book_id from books order by book_id) as T)) order by book_id;

select top 10 * from books where (book_cal='数学' and book_id > (select max(book_id) from (select top 2 book_id from books where book_cal='数学' order by book_id) as T));
