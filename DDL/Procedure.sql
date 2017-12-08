--Member Table Insert Procedure
create or replace procedure member_proc(nm in MEMBERS.M_NAME%type,msid in MEMBERS.M_SID%type,dept in MEMBERS.M_DEPT%type,addr in MEMBERS.ADDRESS%type,
										email in MEMBERS.EMAIL_ID%type,cont in MEMBERS.CONTACT_NO%type,mtype in MEMBERS.MEMBER_TYPE%type)
is
begin
  insert into members(m_name,m_sid,m_dept,address,email_id,contact_no,member_type) values(nm,msid,dept,addr,email,cont,mtype);
end;

--Client Table Insert Procedure
create or replace procedure client_proc(uname in Client.U_Name%type,pass in client.U_Password%type)
is
begin
  insert into client(U_Name,U_Password,U_ID) values(uname,pass);
end;

--Publisher Table Insert PROCEDURE
create or replace procedure publisher_proc(pname in Client.p_Name%type,addr in client.address%type,cno in client.contact_no%type
										,email in client.email_id%type)
is
begin
  insert into publisher(p_name,address,contact_no,email_id) values(pname,addr,cno,email);
end;


--Books_Group Table Insert PROCEDURE
create or replace procedure books_group_proc(bgid in Books_Group.G_ID%type,pid in Books_Group.Publisher_ID%type,
										t in Books_Group.Title%type,a in Books_Group.Author%type, q in Books_Group.No_of_copies%type)
is
begin
	insert into books_group(g_id,publisher_id,title,author,no_of_copies) values(bgid,pid,t,a,q);
end;

--Books Table Insert PROCEDURE
create or replace procedure books_proc(bid in Books.Book_ID%type,bgid in Books.Book_GID%type,
										e in Books.Edition%type, i in Books.Issued%type)
is
begin
	insert into books(book_id,book_gid,edition,issued) values(bid,bgid,e,i);
end;

--Update Books_Group Table PROCEDURE
create or replace procedure books_group_update(q in Books_Group.No_of_copies%type,bgid in Books_Group.G_ID%type)
is
begin
	update books_group set no_of_copies=q where g_id=bgid;
end;

--Update Books Table PROCEDURE
create or replace procedure books_update(i in Books.Issued%type,bgid in Books.Book_GID%type,bid in Books.Book_ID%type)
is
begin
	update books_group set issued=i where book_gid=bgid and book_id=bid;
end;

--Borrowed_By Table Insert PROCEDURE
create or replace procedure borrowed_by_proc(bid in Borrowed_By.B_ID%type,mid in Borrowed_By.Mem_ID%type,bgid in Borrowed_By.BG_ID%type)
is
begin
	insert into borrowed_by(B_ID,Mem_ID,BG_ID) values(bid,mid,bgid);
end;