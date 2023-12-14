show tables;

create table user (
	idx int not null auto_increment primary key,
	mid varchar(20) not null,
	name varchar(20) not null,
	age int default 20,
	address varchar(10)
);

desc user;

INSERT INTO user values(default,'aaa', '에에에', 22, '서울');
INSERT INTO user values(default,'bbb', '비비비', 22, '서울');
INSERT INTO user values(default,'ccc', '씨씨씨', 22, '서울');
select * from user;

delete from user where idx = 11;