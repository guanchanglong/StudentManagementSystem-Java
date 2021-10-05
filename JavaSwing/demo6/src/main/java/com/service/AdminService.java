package com.service;

import com.entity.Admin;

public interface AdminService {
    Admin login(Admin admin);
    String editPassword(Admin admin, String newPassword);
}
