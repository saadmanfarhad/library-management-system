CREATE TABLE LIB.Client(
   
  U_Name Varchar2(100) NOT NULL,
  U_Password Varchar2(150),
  U_ID NUMBER(6,0),
  CONSTRAINTS PK_User PRIMARY KEY(U_Name,U_Password),
  CONSTRAINTS FK_User FOREIGN KEY(U_Id) REFERENCES Members ON DELETE CASCADE
);

CREATE TABLE LIB.Members(
  
  M_ID Number(6,0),
  M_Name Varchar2(150) NOT NULL,
  M_Dept Varchar2(10) NOT NULL,
  M_SID
  Address Varchar2(100) NOT NULL,  
  Email_ID Varchar2(100) NOT NULL,  
  Contact_No Varchar2(20) NOT NULL,
  Member_Type Varchar2(50) NOT NULL,
  CONSTRAINTS PK_Members PRIMARY KEY(M_ID)
);

create sequence member_id_seq
minvalue 1
increment by 1
start with 1;

drop sequence member_id_seq;

create or replace trigger gen_mem_id
before insert on members
for each row
declare
begin
  if(:new.m_id is null) then
    :new.m_id:=member_id_seq.nextval;
  end if;
end;
/

drop trigger gen_mem_id;

select * from members;

insert into members(m_name,m_dept,address,EMAIL_ID,CONTACT_NO,MEMBER_TYPE) values('Saadman','CSE','Elephant Road','sad','01516','student');
insert into members(m_name,m_dept,address,EMAIL_ID,CONTACT_NO,MEMBER_TYPE) values('Messi','CSE','Elephant Road','sad','01516','student');

delete from members where m_name='Saadman';
delete from members where m_name='Ratul';

create or replace procedure member_proc(nm in MEMBERS.M_NAME%type,dept in MEMBERS.M_DEPT%type,addr in MEMBERS.ADDRESS%type,
                                        email in MEMBERS.EMAIL_ID%type,cont in MEMBERS.CONTACT_NO%type,mtype in MEMBERS.MEMBER_TYPE%type)
is
begin
  insert into members(m_name,m_dept,address,email_id,contact_no,member_type) values(nm,dept,addr,email,cont,mtype);
end;
/

execute member_proc('Ratul','CSE','Eskaton','saad','01516','student');

drop procedure member_proc;

create or replace procedure client_proc(uname in Client.U_Name%type,pass in client.U_Password%type)
is
begin
  insert into client(U_Name,U_Password) values(uname,pass);
end;
/