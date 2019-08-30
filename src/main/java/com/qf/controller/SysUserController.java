package com.qf.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

@Controller
public class SysUserController {
    @Autowired
    private SysUserService userService;

/*    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(){
        return "login";
    }*/


    @RequestMapping(value = "/dealLogin",method = RequestMethod.POST)
    public String login(HttpServletRequest req,
                        @RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("realname") String realname) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password,realname);
        /**
         * 1、查询用户是否存在；
         * 2、用户存在查出用户信息，比对凭证；
         * 3、对输入的凭证信息加密与查出的凭证比较；
         * 4、凭证一致，根据用户名查询该用户的权限集合；
         * 5、将用户信息进行脱密后和权限信息存储（session）；
         * 6、返回登陆成功信息；
         * 使用shiro后，这些步骤统一交给shiro处理
         */
        try {
            subject.login(token);
            if (subject.isAuthenticated()){
                //根据传入的用户名查询用户的所有信息
                SysUser byLoginName = userService.findUserByLoginName(loginName);
                //获取用户的id值
                long userId = byLoginName.getUserId();
                System.out.println(userId);
                //将id存入session中，在前台页面获取
                req.setAttribute("userId",userId);
                req.getSession().setAttribute("userId",userId);
                req.setAttribute("loginName",loginName);
                req.getSession().setAttribute("loginName",loginName);

                System.out.println("登录成功!!!!!!!!!");
                if (realname.equals("会员")){
                    return "redirect:memberView";
                }else if (realname.equals("管理员")){
                    return "redirect:main";
                }else {
                    return "redirect:index";
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "index";
    }

    // 当用户权限不足时，访问的页面
    @RequestMapping("/unOauth")
    public String showUnOauth(){
        return "unauth";
    }
    //用户登出时
    @RequestMapping("/logout")
    public String logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();//清空用户在shiro中的驻留信息
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }
    //普通用户有权访问的模块
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/memberView")
    public String memberView(){
        return "member";
    }
    //会员用户有权访问的模块11
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    //添加新的用户信息
    @RequestMapping("/saveUser")
    public String saveUser(SysUser sysUser){
        sysUser.setUserId(userService.getMaxUserId()+1);
        boolean b = userService.addUser(sysUser);
        return b?"redirect:loadUser":"error";
    }

    //在用户管理界面显示所有的用户
    @RequestMapping("/loadUser")
    public String loadUser(Model model,
                           @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum,6);
        List<SysUser> userList = userService.loadAll();
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("userList",userList);
        return "user";

    }

    //根据用户id删除当前用户信息并返回用户显示界面
    @RequestMapping("deleteUser")
    public String deleteUser(int userId){
        boolean b = userService.delUserByUserId(userId);
        return b?"redirect:loadUser":"error";
    }

    //根据当前用户id查询用户所有信息
    @RequestMapping("loadUserByUserId")
    public String loadUserByUserId(int userId,Model model){
        SysUser sysUser = userService.loadUserByUserId(userId);
        model.addAttribute("updateUser",sysUser);
        return "edit";
    }
    //修改当前用户信息
    @RequestMapping("updateUser")
    public String updateUser(SysUser sysUser){
        boolean b = userService.updateByUser(sysUser);
        return b?"redirect:loadUser":"error";
    }

/*    @RequestMapping("saveUser")
    public String saveUser(SysUser sysUser){
        boolean b = userService.saveUser(sysUser);
        return b?"redirect:loadUser":"error";
    }*/
}
