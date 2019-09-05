package com.twins.lee.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twins.lee.config.shiro.ShiroCasConfiguration;
import com.twins.lee.entity.User;
import com.twins.lee.model.BaseModel;
import com.twins.lee.service.IUserService;
import com.twins.lee.utilites.CodecTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    ShiroCasConfiguration shiroCasConfiguration;
    @Value("${environment}")
    private String environment;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/cas")
     public String casTicket(@RequestParam("ticket") String ticket) {
        Object value = null;
        CasToken casToken = new CasToken(ticket);
        casToken.setRememberMe(true);
         Subject subject =   SecurityUtils.getSubject();
            subject.login(casToken);
            List list = subject.getPrincipals().asList();
            System.out.println(list);
        value = subject.getPrincipal();
        subject.getSession().setAttribute("user",value);
        return "redirect:/user/improv";
    }
    @RequestMapping("/login")
    public String index() {
        return "redirect:" + shiroCasConfiguration.loginUrl;
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public BaseModel<User> login(HttpServletRequest request,
                                 @RequestParam("username") String account,
                                 @RequestParam("password") String password,
                                 @RequestParam("vercode") String vercode) throws UnsupportedEncodingException {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(account, CodecTool.encoder(password));
        token.setRememberMe(true);

        BaseModel<User> resultModel = null;
        try {
            if (!subject.isAuthenticated()) {

                subject.login(token);
            }
             //正常登录
            User user = iUserService.getUserById(1);
            resultModel = new BaseModel<>(BaseModel.Success, user);
        } catch (UnknownAccountException e) {
            //登录失败
//            "redirect:login"
            resultModel = new BaseModel<>(BaseModel.UnknownUser, null, e.getLocalizedMessage());

        } catch (IncorrectCredentialsException e) {
            resultModel = new BaseModel<>(BaseModel.PasswordError, null, e.getLocalizedMessage());
        }catch (LockedAccountException e) {
            resultModel = new BaseModel<>(BaseModel.UserStateError, null, e.getLocalizedMessage());
        }
        return resultModel;
    }

    @RequestMapping("/userList")
    @ResponseBody
    @RequiresRoles({"admin"})
    public IPage<User> useList(@RequestParam(value = "page", required = false, defaultValue = "1") long p) {
        Page page = new Page<User>(p, 2);
        return iUserService.selectUserByPage(page, 1);
    }
}
