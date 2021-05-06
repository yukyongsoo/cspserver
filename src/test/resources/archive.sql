delete
from CSP_ELEMENT_INIT_RULE;
delete
from CSP_ARCHIVE_STORAGE;
delete
from CSP_STORAGE;
delete
from CSP_ARCHIVE;

insert into CSP_ARCHIVE(id, name)
values (1, 'testArchive');
insert into CSP_STORAGE(id, name, type, path)
values (1, 'testStorage', 1, '/Users/yug-yongsu/Desktop/temp');
insert into CSP_ARCHIVE_STORAGE(archive_Id, storage_Id)
values (1, 1);
insert into CSP_ELEMENT_INIT_RULE(RULE_ID, ARCHIVE_ID)
values (1, 1);