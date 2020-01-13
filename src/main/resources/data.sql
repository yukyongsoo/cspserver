insert into CSP_ARCHIVE(id, name)
values (1, 'testArchive');
insert into CSP_STORAGE(id, name)
values (1, 'testStorage');
insert into CSP_ARCHIVE_STORAGE(archive_Id, storage_Id)
values (1, 1);

insert into CSP_CONTENT_TYPE(id, name)
values (1, 'testType');
insert into CSP_ELEMENT_TYPE(id, name)
values (1, 'testType');


insert into CSP_ELEMENT_TYPE_RULE (ELEMENT_TYPE_ID, RULE_ID,RULE_TYPE)
values (1, 1, 1);

insert into CSP_RULE_INIT(RULE_ID, ARCHIVE_ID)
values (1, 1);
