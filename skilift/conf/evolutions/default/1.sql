# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table liftstation (
  id                        integer not null,
  name                      varchar(255),
  plz                       varchar(255),
  type                      varchar(255),
  capacity                  integer,
  constraint pk_liftstation primary key (id))
;

create table user (
  mail                      varchar(255) not null,
  password                  varchar(255),
  payment_method            varchar(255),
  constraint pk_user primary key (mail))
;

create sequence liftstation_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists liftstation;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists liftstation_seq;

drop sequence if exists user_seq;

