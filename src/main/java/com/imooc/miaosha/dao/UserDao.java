package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Long id);

    @Insert("insert into user(`id`,`nickname`,`email`,`password`,`salt`,`head`) values(#{id},#{nickname},#{email},#{password},#{salt},#{head})")
    int save(User user);

}
