package com.maple.guideserver.dao;

import com.maple.guideserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User selectUserByAccAndPw(@Param("account") String account, @Param("password") String password);

    void insertUser(@Param("account") String account, @Param("password") String password);

    void updateUserPlace(@Param("user_id") Integer user_id, @Param("longitude") Double longitude,@Param("latitude") Double latitude);

    User selectUserByAcc(@Param("account") String account);
}
