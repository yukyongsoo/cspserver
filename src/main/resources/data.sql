insert into CSP_ARCHIVE(id, name)
values (1, 'testArchive');
insert into CSP_STORAGE(id, name, type, path)
values (1, 'testStorage', 1, '/Users/yug-yongsu/Desktop/temp');
insert into CSP_ARCHIVE_STORAGE(archive_Id, storage_Id)
values (1, 1);

insert into CSP_TYPE(id, type, name)
values (1, 1, 'testType');
insert into CSP_TYPE_RULE(RULE_ID, TYPE_ID, RULE_TYPE)
values (1, 1, 1);

insert into CSP_ELEMENT_INIT_RULE(RULE_ID, ARCHIVE_ID)
values (1, 1);