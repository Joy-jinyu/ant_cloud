package com.gree.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;
import com.gree.entity.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDAO extends BaseMapper<User> {

    @Select("select * from cbase000 where usid = #{usid} and pawd = #{pawd}")
    @TargetDataSource(DatabaseType.master)
    @ResultMap("userMap")
    User fetchByDSPW(@Param("usid")String usid, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @TargetDataSource(DatabaseType.slave)
    @ResultMap("userMap")
    User fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set usid = #{usid}")
    @TargetDataSource(DatabaseType.master)
    void updateByUsid(@Param("usid")String usid);
}