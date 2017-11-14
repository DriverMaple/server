package com.maple.guideserver.dao;

import com.maple.guideserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User selectUserByAccAndPw(@Param("account") String account, @Param("password") String password);
}
