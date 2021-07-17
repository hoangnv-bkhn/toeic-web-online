use toeiconline;

alter TABLE listenguideline MODIFY COLUMN title varchar(512) not null;
alter TABLE listenguideline MODIFY COLUMN image varchar(512) not null;
alter TABLE listenguideline MODIFY COLUMN content varchar(512) not null;

ALTER TABLE listenguideline ADD UNIQUE (title);
