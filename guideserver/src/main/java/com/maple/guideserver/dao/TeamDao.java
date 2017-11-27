package com.maple.guideserver.dao;

import com.maple.guideserver.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Maple on 2017/11/16.
 */
@Mapper
public interface TeamDao {
    void insertTeam(Team team);

    void deleteTeam(@Param("team_id") Integer team_id);

    Team selectTeamById(@Param("team_id") Integer team_id);

    Team selectTeamByCondition(@Param("team_name") String team_name,@Param("team_pw") String team_pw);

    void addMember(@Param("user_id") Integer user_id,@Param("team_id") Integer team_id,@Param("user_role") Integer user_role);

    void exitTeam(@Param("user_id") Integer user_id,@Param("team_id") Integer team_id);

    Team selectTeamByUser(@Param("user_id") Integer user_id);
}
