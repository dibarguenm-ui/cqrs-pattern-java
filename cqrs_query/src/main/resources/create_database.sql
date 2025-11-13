drop database if exists bd_query;
drop user if exists user_query;
create user user_query with password 'zion120921';
create database bd_query owner user_query;
