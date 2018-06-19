use Book_Manage;
/*drop table dbo.books;
create table books
(
book_id char(13) primary key,
book_name nvarchar(50) not null,
book_author nvarchar(20),
book_date date,
book_from nvarchar(20) not null,
book_loc nvarchar(5) check (book_loc in ('���һ�', '����', '����')) default '����' not null,
book_cal nvarchar(5) check (book_cal in ('��ѧ','��ѧ','�����','ͨ�ŵ���','���ھ���','����')) default '����'
)

insert into books values (9787115418081,'Oracle ���ݿ�����뿪��','��չ��','20160401','�����ʵ������','���һ�','�����')
insert into books values (9787309120042,'����ҵ���ɷ������ۺ�����','����ҵרҵ��Աְҵ�ʸ����о�����','20160101','������ѧ������','����','���ھ���')
insert into books values (1,'a','aa','20160401','�����ʵ������','���һ�','��ѧ')
insert into books values (2,'b','bb','20160101','������ѧ������','����','���ھ���')
insert into books values (3,'c','cc','20160401','�����ʵ������','���һ�','��ѧ')
insert into books values (4,'d','dd','20160101','������ѧ������','����','���ھ���')
insert into books values (5,'e','ee','20160401','�����ʵ������','���һ�','��ѧ')
insert into books values (6,'f','ff','20160101','������ѧ������','����','���ھ���')*/

select book_id ISBN, book_name ͼ������, book_author ����, book_loc ����λ��, book_cal ͼ����� from books;

select top 1 * from books where (book_id > (select max(book_id) from (select top 3 book_id from books order by book_id) as T)) order by book_id;

select top 10 * from books where (book_cal='��ѧ' and book_id > (select max(book_id) from (select top 2 book_id from books where book_cal='��ѧ' order by book_id) as T));
