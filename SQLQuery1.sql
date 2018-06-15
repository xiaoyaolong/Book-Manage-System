drop table dbo.books;
create table books
(
book_id char(13) primary key,
book_name nvarchar(50) not null,
book_author nvarchar(20),
book_date date,
book_from nvarchar(20) not null,
book_loc nvarchar(5) check (book_loc in ('���һ�', '����', '����')) default '����' not null
)

insert into books values (9787115418081,'Oracle ���ݿ�����뿪��','��չ��','20160401','�����ʵ������','���һ�')
insert into books values (9787309120042,'����ҵ���ɷ������ۺ�����','����ҵרҵ��Աְҵ�ʸ����о�����','20160101','������ѧ������','����')

select * from books;