-- 기존 저장프로시져 제거
DROP PROCEDURE IF EXISTS insert_boards;


SELECT * FROM members;


DELIMITER $$

CREATE PROCEDURE insert_boards()
BEGIN
    DECLARE i INT DEFAULT 1;

    -- 1000건의 데이터 삽입
    WHILE i <= 1000 DO
            INSERT INTO boards (title, userid, contents)
            VALUES ('기본 제목', 'abc123', '기본 내용');

            INSERT INTO boards (title, userid, contents)
            VALUES ('기본 제목', '987xyz', '기본 내용');

            INSERT INTO boards (title, userid, contents)
            VALUES ('기본 제목', '냐옹냐옹', '기본 내용');

            -- 루프 증가
            SET i = i + 1;
        END WHILE;
END $$

DELIMITER ;

-- 프로시저 호출
CALL insert_boards();