package com.springboot.study.shiro;

import com.springboot.study.domain.Resources;
import com.springboot.study.domain.Role;
import com.springboot.study.domain.User;
import com.springboot.study.service.ResourcesService;
import com.springboot.study.service.UserRoleService;
import com.springboot.study.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 获取用户的角色和权限信息
 * Created by bamboo on 2017/5/10.
 */
public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Resource
    private UserService userService;

    @Resource
    private ResourcesService resourcesService;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        //查出是否有此用户
        String username = (String)token.getPrincipal();
        User user = userService.selectByUsername(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            // 当验证都通过后，把用户信息放在session里
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("userSession", user);
//            session.setAttribute("userSessionId", user.getId());
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", hasUser);//成功则放入session
         // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验90

        }
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(
                        user, //登录识别串信息
                        user.getPassword(), //密码
                        ByteSource.Util.bytes(username),////盐值
                        getName()  //realm name
                );
        return authenticationInfo;
    }

    /**
     * 权限认证
     *为当前登录的Subject授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
//        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        User user = (User) principalCollection.getPrimaryPrincipal();
//        //到数据库查是否有此对象
//        User user = null;// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        user = userMapper.findByName(loginName);
        if (user != null) {
            List<Role> rlist = userRoleService.listRoleByUid(user.getId());//获取用户角色
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userid",user.getId());
            List<Resources> plist = resourcesService.loadUserResources(map);//获取用户权限(菜单)


            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
            for (Role role : rlist) {
                roleStrlist.add(role.getRoledesc());
            }
            for (Resources resources : plist) {
                perminsStrlist.add(resources.getResurl());
            }

            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(roleStrlist);
            //用户的权限集合
            info.addStringPermissions(perminsStrlist);

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
