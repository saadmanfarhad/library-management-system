--Get Number of Copies Function
create or replace function get_copies(gid in number)
return number
is
	copies number(38,0);
begin
	select no_of_copies into copies
	from books_group
	where g_id=gid;
	return copies;
end;
/