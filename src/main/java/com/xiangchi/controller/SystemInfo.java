package com.xiangchi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ******************  类说明  *********************
 *
 * @author :  fanxiangchi@umftech.com
 * @version :  1.0
 * @class :  com.xiangchi.controller.Hello
 * @since :  1/22/18 6:28 PM
 * ***********************************************
 */
@Controller
@RequestMapping("/sysinfo")
public class SystemInfo {

    @RequestMapping("/{system}")
    public String prop(Model model, @PathVariable String system){

        Map<String, String> map = new HashMap<>();

        if ("env".equals(system)) {
            map = System.getenv();
        } else if ("prop".equals(system)) {
            Properties prop = System.getProperties();
            for (String name : prop.stringPropertyNames()) {
                map.put(name, prop.get(name).toString());
            }
        }

        model.addAttribute("infoMap", map);

        return "sysinfo";
    }

}
