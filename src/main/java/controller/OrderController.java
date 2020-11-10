package controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lenovo on 2020/11/9.
 */
@Controller
@EnableAutoConfiguration
public class OrderController {
    @RequestMapping(value="/order/index",method = RequestMethod.GET)
    public String index() {
        return "order/index";
    }

    @RequestMapping(value="/order/resign",method = RequestMethod.GET)
    public String resign() {
        return "order/resign";
    }
}
