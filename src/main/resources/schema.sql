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
    ARCHIVEID int,
    foreign key (ARCHIVEID) references CSP_ARCHIVE(ID),
    STORAGEID int,
    foreign key (STORAGEID) references CSP_STORAGE(ID)
);

create table CSP_CONTENT_TYPE
(
    ID int auto_increment primary key ,
    NAME varchar(30)
);

create table CSP_CONTENT
(
    ID varchar(45) primary key,
    TYPE varchar(30),
    foreign key (TYPE) references CSP_CONTENT_TYPE(NAME)
);





