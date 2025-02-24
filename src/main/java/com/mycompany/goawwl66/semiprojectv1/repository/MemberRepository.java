package com.mycompany.goawwl66.semiprojectv1.repository;

import com.mycompany.goawwl66.semiprojectv1.domain.Member;
import com.mycompany.goawwl66.semiprojectv1.domain.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface MemberRepository {
    @Insert("insert into members (userid, password, name, email) values (#{userid}, #{password}, #{name}, #{email})")
    int insertMember(MemberDTO member);

    @Select("select * from members where userid=#{userid}")
    Member findByUserid(String userid);

    @Select("select count(userid) from members where userid=#{userid}")
    int countByUserid(String userid);

    @Select("select count(email) from members where email=#{email}")
    int countByEmail(String email);


}
