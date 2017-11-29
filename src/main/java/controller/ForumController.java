package controller;

import entity.Forum;
import jdbc.Dao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/23.
 */
@Controller
@EnableAutoConfiguration
public class ForumController {
    @RequestMapping(value="/forum/list",method = RequestMethod.GET)
    public String list(Map module,Integer page) {
        if(page==null){
            page=1;
        }
        int sumPage=0;
        List<Forum> list= Dao.ListBySql(Forum.class,"select * from forum order by write_time desc");
        String sql1=(page-1)*10+","+page*10;
        List<Forum> listPage= Dao.ListBySql(Forum.class,"select * from forum order by write_time desc limit "+sql1);
        if(list.size()%10==0){
            sumPage=list.size()/10;
        }else {
            sumPage=list.size()/10+1;
        }
        module.put("forumList",listPage);
        module.put("sumPage",sumPage);
        module.put("page",page);
        module.put("sumTiao",list.size());
        return "forum/list";
    }
    @RequestMapping(value="/forum/add",method = RequestMethod.GET)
    public String add(Map module,HttpServletRequest request) {
        return "forum/add";
    }
    @RequestMapping(value="/forum/save",method = RequestMethod.GET)
    public String save(Map module,HttpServletRequest request,String title,String content,HttpServletResponse response) {
        HttpSession session = request.getSession();
        int uid=Integer.parseInt(session.getAttribute("uid").toString());
        String name=session.getAttribute("name").toString();
        String writeTime= DataUtil.getStringTime(new Date());
        Forum forum=new Forum();
        forum.setContent(content);
        forum.setTitle(title);
        forum.setUid(uid);
        forum.setUname(name);
        forum.setWriteTime(writeTime);
        Dao.insert(forum);
        try {
            response.sendRedirect("/forum/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/forum/reply",method = RequestMethod.GET)
    public String reply(Map module,HttpServletRequest request,int fid) {
        Forum forum=Dao.load(Forum.class,fid);
        module.put("forum",forum);
        return "forum/reply";
    }
}
