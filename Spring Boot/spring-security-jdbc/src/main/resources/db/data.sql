insert into users(username, password, enabled) values ("user", "pass",true),("admin","pass",true);

insert into authorities(username, authority)
values ("user", "ROLE_USER"), ("admin", "ROLE_ADMIN");