package com.catt.oauth2authserver.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@DS("xiaxia")
public interface UserMapper extends BaseMapper {

    @Select("select S_NAME, S_ROLES , S_PASSWORD from userinfo where s_name = #{userName}")
    List<Map<Object, Object>> selectUserInfoByMyWrapper(@Param("userName") String userName);
}
