package controller;

import com.alibaba.fastjson.JSON;
import entity.User;
import jdbc.Dao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/20.
 */
@RestController
public class AJaxUser {
    @RequestMapping(value="/ajax/againName",method = RequestMethod.POST)
    public String againName(String name,Map module) {
        User user= Dao.get(User.class, "select*from user where name=?", name);
        if(user==null){
            module.put("isRight","no");
        }else{
            module.put("isRight","yes");
        }
        String jsonObject = JSON.toJSONString(module);
        return jsonObject;
    }

    @RequestMapping(value="/ajax/againAccount",method = RequestMethod.POST)
    public String againAccount(String account,Map module) {
        User user= Dao.get(User.class, "select*from user where account=?", account);
        if(user==null){
            module.put("isRight","no");
        }else{
            module.put("isRight","yes");
        }
        String jsonObject = JSON.toJSONString(module);
        return jsonObject;
    }
}
