package com.xupt.demo.service;

import com.xupt.demo.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> login(String name,String password);

    int modifyPassword(String name,String password);
}
