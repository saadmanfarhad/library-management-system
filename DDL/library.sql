CREATE TABLE LIB.Client(
   
  U_Name Varchar2(100) NOT NULL,
  U_Password Varchar2(150),
  U_Id NUMBER(4,0),
  CONSTRAINTS PK_User PRIMARY KEY(U_Name,U_Password),
  CONSTRAINTS FK_User FOREIGN KEY(U_Id) REFERENCES Members ON DELETE CASCADE
);
CREATE TABLE LIB.Admin (
   
  A_Name Varchar2(100) NOT NULL,
  A_Password Varchar2(150),
  CONSTRAINTS PK_Admin  PRIMARY KEY(A_Name,A_Password)
);

CREATE TABLE LIB.Publisher (
   
  P_ID Number(6,0),
  P_Name Varchar2(100) NOT NULL,
  Address Varchar2(150),
  Contact_No VARCHAR2(20) NOT NULL,
  Email_ID Varchar2(100) NOT NULL,
  
  CONSTRAINTS PK_Publisher PRIMARY KEY(P_ID)
  
);
CREATE TABLE LIB.Books_Group(
  
  G_ID Number(10,0),
  Publisher_ID Number(6,0) NOT NULL,
  Title Varchar2(100) NOT NULL,
  Author Varchar2(150) NOT NULL,
  No_of_copies Number(38,0),
  
  CONSTRAINTS PK_Books_Group PRIMARY KEY(G_ID),
  CONSTRAINTS FK_Books_Group FOREIGN KEY(Publisher_ID) REFERENCES LIB.Publisher(P_ID) ON DELETE CASCADE
  
);

CREATE TABLE LIB.Books(

  Book_ID Number(10,0) NOT NULL,
  Book_GID Number(10,0) NOT NULL,
  Edition Varchar2(50) NOT NULL,
  Issued Varchar2(1) NOT NULL,
  
  CONSTRAINTS PK_Books PRIMARY KEY(Book_ID),
  CONSTRAINTS FK_Books FOREIGN KEY(Book_GID) REFERENCES LIB.Books_Group(G_ID) ON DELETE CASCADE

);



CREATE TABLE LIB.Members(
  
  M_ID Number(4,0),
  M_Name Varchar2(150) NOT NULL,
  M_SID NUMBER(6,0) NOT NULL,
  M_Dept Varchar2(10) NOT NULL,
  Address Varchar2(100) NOT NULL,  
  Email_ID Varchar2(100) NOT NULL,  
  Contact_No Varchar2(20) NOT NULL,
  Member_Type Varchar2(50) NOT NULL,
  CONSTRAINTS PK_Members PRIMARY KEY(M_ID)
);

--M:M Relationship

CREATE TABLE LIB.Borrowed_By (
   
  B_ID Number(10,0),
  Mem_ID Number(6,0),
  BG_ID Number(10,0),

  CONSTRAINTS PK_Borrow PRIMARY KEY(B_ID,Mem_ID),
  CONSTRAINTS FK_Borrow_Books FOREIGN KEY(B_ID) REFERENCES LIB.Books(Book_ID),
  CONSTRAINTS FK_Borrow_Members FOREIGN KEY(Mem_ID) REFERENCES LIB.Members(M_ID),
  CONSTRAINTS FK_Borrow_Books_Group FOREIGN KEY(BG_ID) REFERENCES LIB.Books_Group(G_ID)
 
);