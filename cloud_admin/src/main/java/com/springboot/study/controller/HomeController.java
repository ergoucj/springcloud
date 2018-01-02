package com.springboot.study.controller;

import com.springboot.study.common.beans.UserSessionConstant;
import com.springboot.study.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqj on 2017/4/21.
 */
@Controller
public class HomeController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, User user){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            Session session = SecurityUtils.getSubject().getSession();
             user = (User) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("userSession", user);
            session.setAttribute("userSessionId", user.getId());
            SSO(user, session);
            return "redirect:usersPage";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    private void SSO(User user, Session session) {
        //用户单点(PC)
        String uSession = (String) redisTemplate.opsForValue().get(UserSessionConstant.USER_SESSION_PC+ user.getId());
        if (uSession != null && !"".equals(uSession)){
            String key = UserSessionConstant.PC_SHIRO_REDIS_SESSION + uSession;
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(UserSessionConstant.USER_SESSION_PC + user.getId(), String.valueOf(session.getId()));
        redisTemplate.expire(UserSessionConstant.USER_SESSION_PC + user.getId(), UserSessionConstant.expireTime, TimeUnit.SECONDS);
    }

/*    @RequestMapping(value="/logout.json",method=RequestMethod.POST)
    @ResponseBody
    public String logout() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return JSONUtils.toJSONString(result);
    }*/

    @GetMapping(value={"/usersPage"})
    public String usersPage(){
        return "user/users";
    }

    @GetMapping("/rolesPage")
    public String rolesPage(){
        return "role/roles";
    }

    @GetMapping("/resourcesPage")
    public String resourcesPage(){
        return "resources/resources";
    }
}
