package com.dao;

import com.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminDao {

    /**
     * 系统管理员用户登录
     * @param admin 输入的用户信息
     * @return 返回的用户信息
     */
    @Select("select * from s_admin where name=#{admin.name} and password=#{admin.password}")
    Admin login(@Param("admin") Admin admin);

    /**
     * 修改密码，先判断输入的原密码是否错误
     * @param admin 输入的id和密码
     * @return 如果返回值不为空的话，则原密码正确
     */
    @Select("select * from s_admin where id=#{admin.id} and password=#{admin.password}")
    Admin editFindPassword(@Param("admin") Admin admin);

    /**
     * 修改密码，当确定原密码输入正确之后，将旧密码进行修改
     * @param newPassword 新密码
     * @param adminId 对应的用户id
     * @return 返回修改结果
     */
    @Update("update s_admin set password = #{newPassword} where id = #{adminId}")
    int updatePassword(@Param("newPassword") String newPassword,@Param("adminId") int adminId);

}
