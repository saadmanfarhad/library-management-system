--Member ID Sequence
create sequence member_id_seq
minvalue 1
increment by 1
start with 1;

--Member ID Trigger
create or replace trigger gen_mem_id
before insert on client
for each row
declare
begin
  if(:new.m_id is null) then
    :new.m_id:=member_id_seq.nextval;
  end if;
end;

--CLIENT ID Sequence
create sequence client_id_seq
minvalue 1
increment by 1
start with 1;

--CLIENT ID Trigger
create or replace trigger gen_client_id
before insert on client
for each row
declare
begin
  if(:new.u_id is null) then
    :new.u_id:=client_id_seq.nextval;
  end if;
end;

--PUBLISHER ID Sequence
create sequence publisher_id_seq
minvalue 1
increment by 1
start with 1;

--PUBLISHER ID Trigger
create or replace trigger gen_client_id
before insert on publisher
for each row
declare
begin
  if(:new.p_id is null) then
    :new.p_id:=publisher_id_seq.nextval;
  end if;
end;