CREATE TABLE LIB.DEPTS(
ID NUMBER(1,0),
NAME VARCHAR2(100),
CONSTRAINTS DEPT_ID  PRIMARY KEY(ID)
);
CREATE TABLE LIB.STUDENTS(
ID NUMBER(6,0),
NAME VARCHAR2(100),
DEPT_ID NUMBER(1,0),
CONSTRAINTS PK_STUDENTS PRIMARY KEY(ID),
CONSTRAINTS FK_STUDENTS FOREIGN KEY(DEPT_ID) REFERENCES LIB.DEPTS(ID) ON DELETE CASCADE
);
CREATE TABLE LIB.COURSES(
ID VARCHAR2(20) PRIMARY KEY,
NAME VARCHAR2(20) NOT NULL
);

--M:M RELATIONSIP
CREATE TABLE LIB.STUDENTS_COURSES
( SID NUMBER(6,0),
  CID VARCHAR2(20),
  CONSTRAINTS PK_STU_COUR PRIMARY KEY(SID,CID),
  CONSTRAINTS FK_STU FOREIGN KEY(SID) REFERENCES LIB.STUDENTS(ID),
  CONSTRAINTS FK_COURSE FOREIGN KEY(CID) REFERENCES LIB.COURSES(ID)
  ); 

