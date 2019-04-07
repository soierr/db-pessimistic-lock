create sequence REQUEST_SEQ increment by 50 start with 1;

create table request(
code number(5) PRIMARY KEY,
name varchar(32),
sla number(5));