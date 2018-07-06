-- 게시판, 댓글, 회원가입을 위한 테이블, 시퀀스 삭제
DROP TABLE reply2;
DROP TABLE board2;
DROP TABLE members2;
DROP SEQUENCE board2_seq;
DROP SEQUENCE reply2_seq;

-- 게시판, 댓글, 회원가입을 위한 테이블, 시퀀스 생성
CREATE TABLE members2 (
	userid 		VARCHAR2(100) PRIMARY KEY,
	userpwd 	VARCHAR2(100) NOT NULL,
	username 	VARCHAR2(100) NOT NULL,
	gender 		VARCHAR2(50) DEFAULT 'male',
	hobby 		VARCHAR2(2000),
	birth 		DATE,
	age 		NUMBER,
	phone 		VARCHAR2(15),
	address 	VARCHAR2(2000),
	introduce 	VARCHAR2(2000)
);

INSERT INTO members2 
VALUES (
	'aaa',
	'aaa',
	'홍길동',
	'male',
	'fishing reading',
	'90/01/01',
    	32,
	'01011112222',
	'서울시 강동구',
	'내 이름은 홍길동'
);

COMMIT;

--게시판 글정보 테이블
CREATE table board2 (
	boardnum 	number 		primary key,		--게시글 일련번호
	userid 		varchar2(20) 	not null,  		--작성자 이름
	title 		varchar2(200) 	not null, 		--글제목
	content 	varchar2(2000) 	not null,  		--글내용
	hitcount 	number default 0, 			--조회수
	regdate 	date default sysdate ,			--작성일
	originalfile	varchar2(200),				--첨부파일명 (원래 이름)
	savedfile	varchar2(200)				--첨부파일명 (실제 저장된 이름)
);

--게시판 글 일련번호에 사용할 시퀀스 생성
create sequence board2_seq;

--게시판 글 저장 예
insert into board2 (boardnum, userid, title, content)
values (board2_seq.nextval, 'aaa', '글제목', '글내용입니다.');

--리플 정보 테이블
create table reply2 (
	replynum	number	primary key,   		--리플 일련번호
	boardnum	number  not null, 		--본문글번호
	userid		varchar2(20) not null, 		--리플 작성자 이름 
	replytext	varchar2(1000) not null,  	--리플내용
	regdate		date default sysdate,
		constraint reply_num_fk foreign key(boardnum) 
		references board2(boardnum) on delete cascade
);

--리플 테이블 일련번호에 사용할 시퀀스 생성
create sequence reply2_seq;

--리플 저장 예
insert into reply2 values (reply2_seq.nextval, 1, '홍길동', '리플내용', sysdate);
commit;