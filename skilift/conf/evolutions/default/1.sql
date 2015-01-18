# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table liftstation (
  id                        integer not null,
  name                      varchar(255),
  plz                       integer,
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


create table user_liftstation (
  user_mail                      varchar(255) not null,
  liftstation_id                 integer not null,
  constraint pk_user_liftstation primary key (user_mail, liftstation_id))
;
create sequence liftstation_seq;

create sequence user_seq;




alter table user_liftstation add constraint fk_user_liftstation_user_01 foreign key (user_mail) references user (mail) on delete restrict on update restrict;

alter table user_liftstation add constraint fk_user_liftstation_liftstati_02 foreign key (liftstation_id) references liftstation (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists liftstation;

drop table if exists user;

drop table if exists user_liftstation;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists liftstation_seq;

drop sequence if exists user_seq;

