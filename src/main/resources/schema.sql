drop table if exists CSP_ELEMENT_FILE;
drop table if exists CSP_ELEMENT;

drop table if exists CSP_RULE_INIT;
drop table if exists CSP_ELEMENT_TYPE_RULE;
drop table if exists CSP_ELEMENT_TYPE;

drop table if exists CSP_CONTENT;
drop table if exists CSP_CONTENT_TYPE;

drop table if exists CSP_ARCHIVE_STORAGE;
drop table if exists CSP_STORAGE;
drop table if exists CSP_ARCHIVE;

create table CSP_ARCHIVE
(
    ID   int auto_increment primary key,
    NAME varchar(30) unique not null
);

create table CSP_STORAGE
(
    ID   int auto_increment primary key,
    NAME varchar(30) unique not null,
    TYPE int not null,
    PATH varchar(30) not null,
    USABLE boolean not null default true
);

create table CSP_ARCHIVE_STORAGE
(
    ARCHIVE_ID int not null,
    foreign key (ARCHIVE_ID) references CSP_ARCHIVE (ID),
    STORAGE_ID int not null,
    foreign key (STORAGE_ID) references CSP_STORAGE (ID)
);

create unique index INDEX_ARCHIVE_STORAGE_CONSTRAINT ON CSP_ARCHIVE_STORAGE (archive_id, storage_id);

create table CSP_CONTENT_TYPE
(
    ID   int auto_increment primary key,
    NAME varchar(30) unique not null
);

create table CSP_CONTENT
(
    ID      varchar(45) primary key,
    NAME varchar(255) not null,
    TYPE_ID varchar(30) not null,
    foreign key (TYPE_ID) references CSP_CONTENT_TYPE (ID)
);

create table CSP_ELEMENT_TYPE
(
    ID   int auto_increment primary key,
    NAME varchar(30) unique not null
);

create table CSP_ELEMENT_TYPE_RULE
(
    RULE_ID         int auto_increment primary key,
    ELEMENT_TYPE_ID int not null,
    foreign key (ELEMENT_TYPE_ID) references CSP_ELEMENT_TYPE (ID),
    RULE_TYPE       int not null
);

create index INDEX_ELEMENT_TYPE_ID on CSP_ELEMENT_TYPE_RULE (ELEMENT_TYPE_ID);

create table CSP_RULE_INIT
(
    RULE_ID    int not null,
    foreign key (RULE_ID) references CSP_ELEMENT_TYPE_RULE (RULE_ID),
    ARCHIVE_ID int not null,
    foreign key (ARCHIVE_ID) references CSP_ARCHIVE (ID)
);

CREATE INDEX INDEX_INIT_RULE_ID ON CSP_RULE_INIT (RULE_ID);

create table CSP_ELEMENT
(
    ID         int auto_increment primary key,
    NAME       varchar(128) not null,
    CONTENT_ID varchar(45)  not null,
    foreign key (CONTENT_ID) references CSP_CONTENT (ID),
    TYPE       int          not null,
    foreign key (TYPE) references CSP_ELEMENT_TYPE (ID)
);

CREATE INDEX INDEX_ELEMENT_CONTENT_ID ON CSP_ELEMENT (CONTENT_ID);

create table CSP_ELEMENT_FILE
(
    ELEMENT_ID   int          not null,
    foreign key (ELEMENT_ID) references CSP_ELEMENT (ID),
    ARCHIVE_ID int not null,
    foreign key (ARCHIVE_ID) references CSP_ARCHIVE (ID),
    STORAGE_ID int not null,
    foreign key (STORAGE_ID) references CSP_STORAGE (ID),
    STORAGE_PATH    varchar(45)  not null,
    FILE_PATH    varchar(255) not null
);

CREATE INDEX INDEX_FILE_ELEMENT_ID ON CSP_ELEMENT_FILE (ELEMENT_ID);
CREATE UNIQUE INDEX INDEX_ELEMENT_FILE_CONSTRAINT ON CSP_ELEMENT_FILE(ELEMENT_ID,ARCHIVE_ID);

