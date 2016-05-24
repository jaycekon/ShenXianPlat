/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.controller;

import com.Shop.beans.User;
import com.Shop.service.UserService;
import com.Shop.utils.Pager;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
//@SessionAttributes("loginUser")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value="/")
    public String index(){
        return "index/index";
    }
    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model) {
        return "register/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user) {
        JsonObject object = new JsonObject();
        object.addProperty("status",1);
        System.out.println(object.toString());
        if(user!=null){
            userService.addUser(user);
            object.addProperty("hint","添加用户成功");
        }
        return object.toString();
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
//       if(loginUser!=null){
//           if(loginUser.getType()==1)
        userService.deleteUser(id);
//       }
        return "redirect:addUser";
    }
    
    @RequestMapping(value="/index")
    public String index1(){
        return "index/index";
    }

    /**
     * 登录用户
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model) {
        return "login/login";
    }
    
    

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public User loginUser(Model model, User user,HttpSession session) {
        User u = userService.loginUser(user.getPhone(), user.getPassword());
        if (u != null) {
//            model.addAttribute("loginUser", u);
             session.setAttribute("loginUser", u);
        }
        return u ;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public String updateUser(Model model, @ModelAttribute("loginUser") User loginUser) {
        if (loginUser == null) {
            return "user/login";
        }
        return "user/update";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("loginUser") User loginUser, String password, String Email, String sex, String nickname, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        loginUser.setEmail(Email);
        loginUser.setSex(sex);
        loginUser.setNickname(nickname);
        loginUser.setPassword(password);

        if (file != null) {
            String path = request.getSession().getServletContext().getRealPath("app/img/user");
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path, fileName);
            if (targetFile.exists()) {
                targetFile.mkdirs();
            }
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginUser.setImg(fileName);
        }
        userService.updateUser(loginUser);
        return "user/update";
    }
    
    /**
     * 测试json
     */
//    @RequestMapping(value="/test")
//    public String testJson(){
//        return "user/test";
//    }
//    
//    @RequestMapping("/json")
//    public void showInfoJson(User user, HttpServletRequest request,
//        HttpServletResponse response) {
//    String result =
//        "{\"name\":\""+user.getPhone()+"\",\"pwd\":\""+user.getPassword()+"\"}";//user
//        //接到前台传到的数据，并拼接成新的json对象 
//    response.setContentType("application/json");//设置response的传输格式为json 
//    System.out.println(result); 
//    try { 
//         PrintWriter out = response.getWriter(); 
//         out.write(result);//给页面上传输json对象 
//    } catch (IOException e) { 
//         e.printStackTrace(); 
//    } 
//}   


    
    @RequestMapping(value="/users")
    public String test(){
        return "index";
    }
    
    @ResponseBody
    @RequestMapping(value="/test")
    public List getUserList(HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        response.setContentType("application/json");
        Pager pager = new Pager();
        pager.setOffset(0);
        pager.setMaxPageSize(7);

        pager=userService.listUser(pager);
        List list =pager.getDatas();
        System.out.println("-----------");
        System.out.println(pager.getDatas().toString());
        map.put("list",list);
        return pager.getDatas();
    }

    @RequestMapping(value="/test/{name}", method = RequestMethod.GET)
    public @ResponseBody User getShopInJSON(@PathVariable String name,HttpServletResponse response) {
        User user=new User();
        user.setNickname(name);


        return user;

    }
}
