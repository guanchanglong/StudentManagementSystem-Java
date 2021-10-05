package com.service.impl;

import com.dao.AdminDao;
import com.entity.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(Admin admin){
        return adminDao.login(admin);
    }

    @Override
    public String editPassword(Admin admin, String newPassword){
        String resultString;
        Admin originalAdmin = adminDao.editFindPassword(admin);
        if (originalAdmin==null){
            resultString = "旧密码错误！";
        }else{
            int flag = adminDao.updatePassword(newPassword,originalAdmin.getId());
            if (flag!=0){
                resultString = "密码修改成功！";
            }else{
                resultString = "修改失败！";
            }
        }
        return resultString;
    }
}
