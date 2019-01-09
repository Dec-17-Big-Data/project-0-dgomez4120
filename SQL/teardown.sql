alter table accounts drop constraint user_id_fk1;
alter table transactions drop constraint user_id_fk2;
alter table transactions drop constraint account_number_fk;

drop table users;
drop table accounts;
drop table transactions;

drop sequence user_id_sequence;
drop sequence account_number_sequence;
drop sequence transaction_id_sequence;
