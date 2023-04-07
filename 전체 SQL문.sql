use ticatcher;

create table tcc_member(
                           mem_idx int primary key auto_increment comment '회원 고유번호',
                           mem_id varchar(50) unique not null comment '회원 아이디',
                           mem_pw varchar(50) not null comment '회원 비밀번호',
                           mem_name varchar(40) not null comment '회원 이름',
                           mem_gen varchar(6) not null comment '회원 성별',
                           mem_tel varchar(11) not null comment '회원 전화번호',
                           mem_birth varchar(8) not null comment '회원 생년월일',
                           mem_email varchar(100) not null comment '회원 이메일',
                           mem_aka varchar(50) not null comment '회원 닉네임',
                           mem_regdate datetime not null default current_timestamp() comment '회원 가입일자'
)engine=InnoDB default charset=utf8;

create table tcc_schedule(
                             schedule_idx int primary key auto_increment comment '공연일정 고유번호',
                             schedule_date date not null comment '공연일정 일자',
                             schedule_time time not null comment '공연일정 시간',
                             stage_idx int not null comment '연극 고유번호',
                             theater_idx int not null comment '극장 고유번호',
                             foreign key(stage_idx) references tcc_stage(stage_idx),
                             foreign key(theater_idx) references tcc_theater(theater_idx)
)engine=InnoDB default charset=utf8;

-- create table tcc_time(
-- time_idx int primary key auto_increment comment '공연 시간 고유번호',
-- time_date date not null comment '공연 일자',
-- time_starttime time not null comment '공연 시작 시간',
-- time_endtime time not null comment '공연 종료 시간',
-- time_count int not null comment '공연 회차'
-- )engine=InnoDB default charset=utf8;
--
-- drop table tcc_time ;

create table tcc_stage(
                          stage_idx int primary key auto_increment comment '연극 고유번호',
                          stage_name varchar(100) not null comment '연극 제목',
                          stage_grade varchar(50) not null comment '연극 관람등급',
                          stage_runtime varchar(20) not null comment '연극 러닝타임',
                          stage_host varchar(100) not null comment '연극 주최자(사)',
                          stage_posterName varchar(150) not null comment '연극 포스터 파일이름',
                          stage_posterPath text not null comment '연극 포스터 경로',
                          stage_imgInfoName varchar(150) comment '연극 설명 이미지 파일 이름',
                          stage_imgInfoPath text comment '연극 설명 이미지 파일 경로',
                          stage_info varchar(100) not null comment '연극 간단 설명'
)engine=InnoDB default charset=utf8;

create table tcc_price(
                          price_idx int primary key auto_increment comment '가격 고유번호',
                          schedule_idx int not null comment '공연일정 고유번호',
                          price_name varchar(30) not null comment '가격 이름',
                          price_price int not null comment '가격',
                          foreign key(schedule_idx) references tcc_schedule(schedule_idx)
)engine=InnoDB default charset=utf8;

create table tcc_theater(
                            theater_idx int primary key auto_increment comment '극장 고유번호',
                            theater_name varchar(100) not null comment '극장 이름',
                            theater_hallcount int not null comment '극장 공연장 수',
                            theater_audience int not null comment '극장 총 관객수',
                            theater_year varchar(4) comment '극장 개관연도',
                            theater_addr varchar(100) comment '극장 주소',
                            theater_sido varchar(30) comment '극장 시도 주소',
                            theater_gugun varchar(30) comment '극장 구군 주소'
)engine=InnoDB default charset=utf8;

create table tcc_theater_hall(
                                 hall_idx int primary key auto_increment comment '홀 고유번호',
                                 hall_name varchar(100) not null comment '공연장 이름',
                                 hall_audience int not null comment '공연장 관객수',
                                 theater_idx int not null comment '극장 고유번호',
                                 foreign key(theater_idx) references tcc_theater(theater_idx)
);

create table tcc_board(
                          board_idx int primary key auto_increment comment '게시글 고유번호',
                          board_config varchar(10) not null comment '게시글 분류',
                          board_conidx int comment '게시글 분류별 고유번호',
                          board_name varchar(40) not null comment '게시글 제목',
                          board_contents text not null comment '게시글 내용',
                          board_attachName text comment '게시글 첨부파일 이름',
                          board_attachPath text comment '게시글 첨부파일 경로',
                          board_regdate datetime not null default current_timestamp() comment '게시글 게시일시',
                          board_readcount int default 0 comment '게시글 조회수',
                          mem_idx int not null comment '회원 고유번호',
                          foreign key(mem_idx) references tcc_member(mem_idx)
);

create table tcc_qna(
                        qna_idx int primary key auto_increment comment '문의사항 고유번호',
                        qna_memidx int not null comment '문의사항 회원별 고유번호',
                        qna_name varchar(40) not null comment '문의사항 제목',
                        qna_contents text not null comment '문의사항 내용',
                        qna_answer text comment '문의사항 답변',
                        qna_attachName varchar(150) comment '문의사항 첨부파일 이름',
                        qna_attachPath text comment '문의사항 첨부파일 경로',
                        qna_regdate datetime not null default current_timestamp() comment '문의사항 게시일시',
                        mem_idx int not null comment '회원 고유번호',
                        foreign key(mem_idx) references tcc_member(mem_idx)
);

-- create table tcc_sale(
-- sale_idx int primary key auto_increment comment '할인정보 고유번호',
-- sale_name varchar(20) not null comment '할인정보 이름',
-- sale_rate int not null comment '할인정보 할인율'
-- )engine=InnoDB default charset=utf8;
--
-- drop table tcc_sale ;

-- create table tcc_stageinfo(
-- stageinfo_idx int primary key auto_increment comment '공연정보 고유변호',
-- stage_idx int not null comment '연극 고유번호',
-- time_idx int not null comment '공연 시간 고유번호',
-- theater_idx int not null comment '극장 고유번호',
-- sale_idx int not null comment '할인정보 고유번호',
-- stageinfo_saleprice int comment '할인 가격 (정가 그대로 시 표기하지 않음)',
-- foreign key(stage_idx) references tcc_stage(stage_idx),
-- foreign key(time_idx) references tcc_time(time_idx),
-- foreign key(theater_idx) references tcc_theater(theater_idx),
-- foreign key(sale_idx) references tcc_sale(sale_idx)
-- )engine=InnoDB default charset=utf8;
--
-- drop table tcc_stageinfo ;

create table tcc_booking(
                            book_idx int primary key auto_increment comment '예약 고유번호',
                            mem_idx int not null comment '회원 고유번호',
                            schedule_idx int not null comment '공연일정 고유번호',
                            price_idx int not null comment '가격 고유번호',
                            book_amountticket int not null comment '구매 티켓 수',
                            book_totalprice int not null comment '총 결제금액',
                            book_success varchar(10) not null comment '결제(성공)여부',
                            foreign key(mem_idx) references tcc_member(mem_idx),
                            foreign key(schedule_idx) references tcc_schedule(schedule_idx),
                            foreign key(price_idx) references tcc_price(price_idx)
)engine=InnoDB default charset=utf8;

create table tcc_review(
                           review_idx int primary key auto_increment comment '리뷰 고유번호',
                           review_name varchar(40) not null comment '리뷰 제목',
                           review_contents text not null comment '리뷰 내용',
                           review_score int not null comment '리뷰 점수',
                           review_attachName varchar(150) comment '리뷰 첨부파일 이름',
                           review_attachPath text comment '리뷰 첨부파일 경로',
                           review_regdate datetime not null default current_timestamp() comment '리뷰 작성날짜',
                           book_idx int not null comment '예약 고유번호',
                           foreign key(book_idx) references tcc_booking(book_idx)
)engine=InnoDB default charset=utf8;

create table tcc_admin(
                          admin_idx int primary key auto_increment comment '관리자 고유번호',
                          admin_id varchar(50) not null comment '관리자 아이디',
                          admin_pw varchar(50) not null comment '관리자 비밀번호',
                          admin_tel varchar(11) not null comment '관리자 연락처',
                          admin_name varchar(20) not null comment '관리자 이름',
                          admin_grade varchar(10) not null comment '관리자 등급',
                          admin_email varchar(100) comment '관리자 이메일'
)engine=InnoDB default charset=utf8;

-- create table tcc_stageInfoImage(
-- sii_idx int primary key auto_increment comment '공연 상세정보 이미지 고유번호',
-- sii_name varchar(150) not null comment '공연 상세정보 이미지 이름',
-- sii_path text comment '공연 상세정보 이미지 경로',
-- stage_idx int comment '연극 고유번호',
-- foreign key(stage_idx) references tcc_stage(stage_idx)
-- )engine=InnoDB default charset=utf8;

create table tcc_delete_id(
                              delete_id_idx int primary key auto_increment comment '탈퇴 회원 아이디 고유번호',
                              delete_id varchar(50) unique not null comment '탈퇴 회원 아이디',
                              delete_date datetime not null default current_timestamp() comment '회원 탈퇴일자'
)engine=InnoDB default charset=utf8;

insert into tcc_admin (admin_id, admin_pw, admin_tel, admin_name, admin_grade, admin_email) values ('admin','ticatcher0401','0212345678','총관리자','super','admin@ticatcher.com');
insert into tcc_admin (admin_id, admin_pw, admin_tel, admin_name, admin_grade, admin_email) values ('admin_1','1234','01012345678','일반관리자1','normal','admin_1@ticatcher.com');
insert into tcc_member (mem_id, mem_pw, mem_name, mem_gen, mem_tel, mem_birth, mem_email, mem_aka) values ('admin','root1234!','관리자','male','0212345678','20230401','admin@ticatcher.com','관리자');




/*_______________________________________________________________________________*/

-- DELIMITER ;; /* 프로시저 만들기 위한 구분문자 DELIMITER 사이에 공백 지우면 에러남 */
CREATE PROCEDURE auto_insert(count int) /*auto_insert 이름의 프로시저 생성 호출시 들어오는 숫자를 count 변수에 저장*/
BEGIN
    DECLARE i INT DEFAULT 1;/* 변수 I에 1 초기화 */
    WHILE (i <= count) DO/*1부터 호출시 입력한 숫자만큼 반복*/
		insert INTO tcc_board(board_config, board_conidx, board_name, board_contents, mem_idx) values ('notice',i,concat(i,'번째 테스트용 공지사항입니다.'),concat(i, '번째 테스트용 공지사항입니다.\n감사합니다.'),1);
        SET i = i + 1; /* 루프가 끝나기전 I변수 1증가 */
END WHILE; /* 반복  */
END;;
-- DELIMITER ; /* 프로시저 종료 지점 마찬가지로 DELIMITER 사이 공백 지우면 에러남.*/
/*_______________________________________________________________________________*/

CALL auto_insert(321);

drop procedure auto_insert;
-- end


/*_______________________________________________________________________________*/

-- DELIMITER ;; /* 프로시저 만들기 위한 구분문자 DELIMITER 사이에 공백 지우면 에러남 */
CREATE PROCEDURE auto_insert(count int) /*auto_insert 이름의 프로시저 생성 호출시 들어오는 숫자를 count 변수에 저장*/
BEGIN
    DECLARE i INT DEFAULT 1;/* 변수 I에 1 초기화 */
    WHILE (i <= count) DO/*1부터 호출시 입력한 숫자만큼 반복*/
		insert INTO tcc_board(board_config, board_conidx, board_name, board_contents, mem_idx) values ('faq',i,concat(i,'번째 테스트용 FAQ입니다.'),concat(i, '번째 테스트용 FAQ입니다.\n감사합니다.'),1);
        SET i = i + 1; /* 루프가 끝나기전 I변수 1증가 */
END WHILE; /* 반복  */
END;;
-- DELIMITER ; /* 프로시저 종료 지점 마찬가지로 DELIMITER 사이 공백 지우면 에러남.*/
/*_______________________________________________________________________________*/

CALL auto_insert(321);

drop procedure auto_insert;
-- end



