package controller;

import entity.User;
import jdbc.Dao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/12.
 */
@Controller
@EnableAutoConfiguration
public class UserController {
    @RequestMapping(value="/user/resign",method = RequestMethod.GET)
   public String resign() {
        return "resign";
    }

    @RequestMapping(value="/user/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/user/index",method = RequestMethod.GET)
    public String index() {
        return "html/index";
    }

    @RequestMapping(value="/user/doLogin",method = RequestMethod.GET)
    public String doLogin(Map module,String password,String account,HttpServletResponse response,HttpServletRequest request) {
        User user= Dao.get(User.class, "select*from user where account=? and password=?", account, password);
        HttpSession session = request.getSession();
        if(user==null){
            module.put("message","<span style='color:red'>账号与密码不匹配</span>");
            return "login";
        }else {
            try {
                session.setAttribute("account",user.getAccount());
                session.setAttribute("name",user.getName());
                session.setAttribute("uid",user.getId());
                response.sendRedirect("/user/myindex");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value="/user/loginOut",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        session.removeAttribute("name");
        session.removeAttribute("uid");
        return "login";
    }

    @RequestMapping(value="/user/myindex",method = RequestMethod.GET)
    public String myindex(HttpServletRequest request,Map module) {
        HttpSession session = request.getSession();
        module.put("name",session.getAttribute("name"));
        return "myindex";
    }
    @RequestMapping(value="/user/doResign",method = RequestMethod.GET)
    public String doResign(String name,String account,String password,Map module) {
        User user=new User();
        user.setName(name);
        user.setAccount(account);
        user.setPassword(password);
        Dao.insert(user);
        module.put("success","注册成功！");
        module.put("user",user);
        return "resign";
    }

}
