--Table Creation
create table users(
    user_id number(10) primary key,
    first_name varchar2(255) not null,
    last_name varchar2(255) not null,
    username varchar2(255) unique not null,
    user_password varchar(255) not null,
    is_super number(1) default(0)
);



insert into users(
    user_id,
    first_name,
    last_name,
    username,
    user_password,
    is_super
    )
    values(
    1,
    'Diego',
    'Gomez',
    'dgomez4120',
    'pssw0rd',
    1
    );
    
insert into users(    
    user_id,
    first_name,
    last_name,
    username,
    user_password,
    is_super
    )values(
    2,
    'Made',
    'Up',
    'mde45',
    'pass',
    0);
    
select last_name from users;
select * from users;
commit;
