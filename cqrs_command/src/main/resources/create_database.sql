drop database if exists bd_command;
drop user if exists user_command;
create user user_command with password 'zion120921';
create database bd_command owner user_command;

