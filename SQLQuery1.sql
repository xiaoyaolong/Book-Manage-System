drop table dbo.books;
create table books
(
book_id char(13) primary key,
book_name nvarchar(50) not null,
book_author nvarchar(20),
book_date date,
book_from nvarchar(20) not null,
book_loc nvarchar(5) check (book_loc in ('鲍家汇', '望府', '长丰')) default '望府' not null
)

insert into books values (9787115418081,'Oracle 数据库管理与开发','尚展垒','20160401','人民邮电出版社','鲍家汇')
insert into books values (9787309120042,'银行业法律法规与综合能力','银行业专业人员职业资格考试研究中心','20160101','复旦大学出版社','望府')

select * from books;