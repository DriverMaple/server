package com.maple.guideserver.controller;

import com.maple.guideserver.Common.ActType;
import com.maple.guideserver.Common.Result;
import com.maple.guideserver.dao.UserDao;
import com.maple.guideserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AllController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Result Login(@RequestBody ActType param){
        Result result = new Result();
        if (param.getAccount() == null || param.equals("") || param.getPassword() == null || param.getPassword().equals("")){
            result.setResult(Result.FAIL);
            result.setMessage("请输入账号密码！");
            return  result;
        }

        String account = param.getAccount();
        String password = param.getPassword();
        System.out.println("账号："+account);
        System.out.println("密码："+password);
        User user = userDao.selectUserByAccAndPw(account,password);
        if (user == null){
            result.setMessage("账号密码错误！");
            result.setResult(Result.FAIL);
            return result;
        }
        result.setResult(Result.SUCCESS);
        result.setValue(user);
        return result;
    }
}
