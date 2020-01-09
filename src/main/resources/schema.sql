drop table if exists CSP_ARCHIVE_STORAGE;
drop table if exists CSP_STORAGE;
drop table if exists CSP_ARCHIVE;
drop table if exists CSP_CONTENT;
drop table if exists CSP_CONTENT_TYPE;

create table CSP_ARCHIVE
(
    ID   int auto_increment primary key,
    NAME varchar(30)
);

create table CSP_STORAGE
(
    ID   int auto_increment primary key,
    NAME varchar(30)
);

create table CSP_ARCHIVE_STORAGE
(
    ARCHIVE_ID int,
    foreign key (ARCHIVE_ID) references CSP_ARCHIVE(ID),
    STORAGE_ID int,
    foreign key (STORAGE_ID) references CSP_STORAGE(ID)
);

create table CSP_CONTENT_TYPE
(
    ID int auto_increment primary key ,
    NAME varchar(30)
);

create table CSP_CONTENT
(
    ID varchar(45) primary key,
    TYPE_ID varchar(30),
    foreign key (TYPE_ID) references CSP_CONTENT_TYPE(ID)
);


create table CSP_ELEMENT_TYPE
(
    ID int auto_increment primary key ,
    NAME varchar(30)
);

create table CSP_ELEMENT
(
    ID int auto_increment primary key ,
    NAME varchar(128),
    CONTENT_ID varchar(45),
    foreign key (CONTENT_ID) references CSP_CONTENT(ID),
    TYPE int,
    foreign key (TYPE) references CSP_ELEMENT_TYPE(ID)
);




