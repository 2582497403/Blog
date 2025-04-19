package com.fanqie.blog.service;

import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.utils.Result;

import java.util.HashMap;

public interface LoginService {
    Result<HashMap<String,String>> login(TbUser user);
}
