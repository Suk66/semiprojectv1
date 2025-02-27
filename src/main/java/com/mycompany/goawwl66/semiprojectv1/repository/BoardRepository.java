package com.mycompany.goawwl66.semiprojectv1.repository;

import com.mycompany.goawwl66.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardRepository {
    @Select("select bno, title, userid, regdate, thumbs, views from boards order by bno desc limit #{stnum}, #{pageSize}")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Select("select ceil(count(bno) / #{pageSize}) from boards")
    int countPagesBoard(int pageSize);
}
