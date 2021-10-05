package com.xupt.demo.service.impl;

import com.xupt.demo.dao.AdminDao;
import com.xupt.demo.entity.Admin;
import com.xupt.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> login(String name, String password){
        return adminDao.findAllByNameAndPassword(name,password);
    }

    @Override
    public int modifyPassword(String name,String password){
        return adminDao.modifyPassword(name,password);
    }

}
