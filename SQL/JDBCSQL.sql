--User Table Creation
create table users(
    user_id number(10) primary key,
    first_name varchar2(255) not null,
    last_name varchar2(255) not null,
    username varchar2(255) unique not null,
    user_password varchar(255) not null,
    is_super number(1) default(0),
    is_open number(1) default(1)
    
    
);

--Accounts Table Creation
create table accounts(
    account_number number(16) primary key,
    user_id number(10) not null,
    account_balance float(10) default(0.00),
    account_creation date default(sysdate),
    is_closed number(1) default(0),
    constraint user_id_fk1 foreign key (user_id) references users (user_id)
);


--Transactions Table Creation
create table transactions(
    transaction_id number(38) primary key,
    user_id number(10) not null,
    account_number number(16) not null,
    is_withdrawl number(1) not null,
    transaction_amount float(10) not null,
    transaction_timestamp timestamp default(systimestamp),
    constraint user_id_fk2 foreign key (user_id) references users (user_id),
    constraint account_number_fk foreign key (account_number) references accounts (account_number)
);




--Trigger: user_id increment
create sequence user_id_sequence;

create or replace trigger user_id_insert
    before insert on users
    for each row
begin
    select user_id_sequence.nextval
    into: new.user_id
    from dual;
end user_id_insert; 
/

--Trigger: account_number increment
create sequence account_number_sequence;

create or replace trigger account_number_insert
    before insert on accounts
    for each row
begin
    select account_number_sequence.nextval
    into: new.account_number
    from dual;
end account_number_insert; 
/

--Trigger: transaction_id increment
create sequence transaction_id_sequence;

create or replace trigger transaction_id_insert
    before insert on transactions
    for each row
begin
    select transaction_id_sequence.nextval
    into: new.transaction_id
    from dual;
end transaction_id_insert; 
/




--Stored Procedure: make transaction
create or replace procedure perform_transaction
(acctNum in number, isWithdrawl in number, transAmount in float, user_in in number, trans_made out number) is
acctBal float(10);
endbalance float(20);
begin
    trans_made := 0;
    select account_balance into acctBal from accounts where account_number = acctNum and user_id = user_in;
        if(isWithdrawl = 1) then
            if(acctBal-transAmount < 0) then
                trans_made := 2;
            else
                insert into transactions(user_id, account_number, is_Withdrawl, transaction_amount)
                values(user_in, acctNum, isWithdrawl, transAmount);
                endbalance := acctBal - transAmount;
                update accounts set account_balance = endbalance where account_number = acctNum and user_id = user_in;
                trans_made := 1;
            end if;
        else
            insert into transactions(user_id, account_number, is_Withdrawl, transaction_amount)
            values(user_in, acctNum, isWithdrawl, transAmount);
            endbalance := acctBal + transAmount;
            update accounts set account_balance = endbalance where account_number = acctNum and user_id = user_in;
            trans_made := 1;
        end if;
    exception
        when no_data_found then
        dbms_output.put_line('No records found');
end; 
/

--Stored Procedure: create user
create or replace procedure create_user
(firstname in varchar2, lastname in varchar2, usern in varchar2, passwd in varchar2) is
begin
    insert into users(first_name, last_name, username, user_password)
    values(firstname, lastname, usern, passwd);
    commit;
end;
/

--Stored Procedure: promote super
create or replace procedure promote_super
(userid in number)  is
begin
    update users set is_super = 1 where user_id = userid;
end;
/

--Stored Procedure: create account
create or replace procedure create_acct
(userid in number) is
begin
    insert into accounts(user_id)values(userid);
end;
/

--Stored Procedure: close account
create or replace procedure close_acct
(userid in number, acctnum in number) is
begin
    update accounts set is_closed = 1 where user_id = userid and account_number = acctnum;
end;
/

--Stored Procedure: deleter user
create or replace procedure delete_user
(userid in number) is
begin
    update users set is_open = 0 where user_id = userid;
end;
/


        
    
    


--First Superuser
insert into users(
    user_id,
    first_name,
    last_name,
    username,
    user_password,
    is_super,
    is_open
    )values(
    1,
    'Diego',
    'Gomez',
    'dgomez4120',
    'pssw0rd',
    1,
    1
);

--First Account
insert into accounts values(1, 1, 3.50, sysdate,0);

set serveroutput on;

--call create_acct(41);

--select user_id_sequence.currval from dual;
--select user_id, first_name, last_name, username, is_super, is_open from users;
commit;
--declare 
--is_success number;
--begin
--perform_transaction(1,0,25,1,is_success);
--dbms_output.put_line(is_success);
--end;
--/

