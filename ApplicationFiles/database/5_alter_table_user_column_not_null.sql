use toeiconline;
alter table user modify column name varchar(255) not null;
alter table user modify column password varchar(255) not null;
alter table user modify column roleid bigint not null;