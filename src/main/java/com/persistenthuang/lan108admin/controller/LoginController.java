package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.Result.ResuleMessage;
import com.persistenthuang.lan108admin.Result.Result;
import com.persistenthuang.lan108admin.pojo.User;
import com.persistenthuang.lan108admin.service.UserServiceImpl;
import com.persistenthuang.lan108admin.util.EmailTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {
    //保存验证码
    private final Map<String, Object> resultMap = new HashMap<>();
    @Autowired
    UserServiceImpl userService;
    @Autowired
    EmailTools emailTools;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "api/authentication")
    public String authentication() {
        log.info("开始测试链接");
        return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, ""));
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("api/logout")
    public String logout() {
        log.info("开始登出");
        Subject subject = SecurityUtils.getSubject();
        try {
            log.info("登出成功");
            subject.logout();
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "登出成功"));
        } catch (Exception e) {
            log.error("登出失败");
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "登出失败"));
        }
    }

    @CrossOrigin
    @PostMapping("/api/login")
    @ResponseBody
    public String login(@RequestBody JSONObject jsonObject) {
        log.info("开始登录");
        //获得账号密码
        String userName = jsonObject.getString("userName");
        log.info("用户名" + userName);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, jsonObject.getString("userPassword"));
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            log.info("登录成功");
            User user = userService.getByName(userName);
            log.info(JSONObject.toJSONString(user));
            return JSONObject.toJSONString(user);
        } catch (Exception e) {
            log.info("登录失败");
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "登录失败"));
        }
    }

    @CrossOrigin
    @PostMapping("/api/email")
    @ResponseBody
    public String sendEmail(@RequestBody JSONObject jsonObject) {
        log.info("开始发送邮件");
        String code = null;
        //获取邮箱
        String userEmail = jsonObject.getString("userEmail");
        if (!userService.isExistByEmail(userEmail)) {
            code = emailTools.sendEmail(userEmail);
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "邮箱已经存在"));
        }
        if (code != null) {
            this.saveCode(code, userEmail);
            return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "邮件发送成功"));
        } else {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "邮件发送失败"));
        }
    }

    @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public String register(@RequestBody JSONObject jsonObject) throws JSONException {
        log.info("开始注册");
        //获取验证码
        String code = jsonObject.getString("code");
        //获取注册对象
        JSONObject jsonUser = jsonObject.getJSONObject("user");
        User user = jsonUser.toJavaObject(User.class);

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", user.getUserPassword(), salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setUserSalt(salt);
        user.setUserPassword(encodedPassword);

        //判断用户名
        if (userService.isExistByName(jsonUser.getString("userName"))) {
            log.info("注册名称已经存在");
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "用户名已存在"));
        }
        //获取保存的验证码
        String requestHash = (String) this.resultMap.get(jsonUser.getString("userEmail"));
        if (requestHash == null) {
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "验证码过期"));
        }
        //获取保存验证码的时间
        String tamp = (String) this.resultMap.get(jsonUser.getString("userEmail") + "temp");
        //当前时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String currentTime = sf.format(c.getTime());
        if (tamp.compareTo(currentTime) > 0) {
            String hash = DigestUtils.md5DigestAsHex(code.getBytes());//生成MD5值
            if (hash.equals(requestHash)) {
                //校验成功
                log.info("注册成功");
                this.userService.add(user);
                return JSONObject.toJSONString(new ResuleMessage(Result.SUCCESS, "注册成功"));
            } else {
                //校验失败
                return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "验证码错误"));
            }
        } else {
            //超时
            return JSONObject.toJSONString(new ResuleMessage(Result.FAILURE, "验证码过期"));
        }
    }

    //保存验证码和时间
    private void saveCode(String code, String userEmail) {
        log.info("保存验证码");
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
        String hash = DigestUtils.md5DigestAsHex(code.getBytes());
        resultMap.put(userEmail, hash);
        resultMap.put(userEmail + "temp", currentTime);
    }

    //每日0点，定时清除验证码
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void deleteCode() {
        log.info("清空验证码");
        this.resultMap.clear();
    }
}
