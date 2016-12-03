package com.Shop.controller;

import com.Shop.DTO.UserDTO;
import com.Shop.Util.OrderDetailPojo;
import com.Shop.Util.OrdersPojo;
import com.Shop.beans.*;
import com.Shop.service.BackStageService;
import com.Shop.service.GoodService;
import com.Shop.service.OrderService;
import com.Shop.service.UserService;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/2.
 */
@Controller
public class BackStageController {
    @Autowired
    private BackStageService backStageService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private OrderService orderService;
    Logger log = Logger.getLogger(this.getClass());



    @RequestMapping(value="addCate",method= RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String addCate(Cate cate, HttpServletRequest request){
        String json="";
        if(request.getParameter("cateName")!=null){
            log.info("加入二级分类");
            String cateName= request.getParameter("cateName");
            Cate c = backStageService.findCateByName(cateName);
            cate.setCate(c);
            json = backStageService.addCates(cate);
        }else {
            log.info("加入一级分类");
            json = backStageService.addCate(cate);
        }
        return json;
    }

    @RequestMapping(value = "setImage",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addGoodDown(@RequestParam("files")MultipartFile files,HttpServletRequest request){
        HttpSession session = request.getSession();
        JsonObject jsonObject = new JsonObject();
        if(session.getAttribute("loginUser")==null){
            jsonObject.addProperty("status","1");
            jsonObject.addProperty("errMsg","用户未登录");
            return jsonObject.toString();
        }
        User user =(User)session.getAttribute("loginUser");
        String path= File.separator+"var"+File.separator+"www"+File.separator+"html"+File.separator+"Shop"+File.separator;
        String fileName = UUID.randomUUID().toString()+".jpg";
        try {
            //获取文件名称
            user.setImg("http://121.42.190.17/Shop/"+fileName);
            userService.updateUser(user);
            //写入本地磁盘
            InputStream is = files.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(new File(path + fileName));
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonObject.addProperty("status","0");
        jsonObject.addProperty("imageUrl","http://121.42.190.17/Shop/"+fileName);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/App/Download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = "1472132579283_head.png";
        System.out.println(filename);

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + filename);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = "/var/www/html/Shop/" + filename;
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();

        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }

        in.close();
        out.close();
    }


    @RequestMapping(value="BackStage/AddProduct",method= RequestMethod.GET)
    public String productAdd(){
        return "backStage/Product/productPublish";
    }

    @RequestMapping(value="BackStage/ListProduct",method= RequestMethod.GET)
    public String listProduct(Model model){
        List<Product> products = goodService.findAll();
        model.addAttribute("products",products);
        return "backStage/Product/productList";
    }


    @RequestMapping(value = "addGoodDown",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String addGoodDown(Product product,@RequestParam("files")MultipartFile[] files){
        String fileName =UUID.randomUUID().toString()+".jpg";
        String file = "http://121.42.190.17/Shop/"+fileName;
        if(files.length>0){
            product.setImage(file);
        }
        goodService.addProduct(product);
        log.info(product.toString());
        log.info("封面图片数量"+files.length);
        String path=File.separator+"var"+File.separator+"www"+File.separator+"html"+File.separator+"Shop"+File.separator;
        for(int i = 0;i<files.length;i++){
            if(!files[i].isEmpty()){
                try {
                    FileUtils.copyInputStreamToFile(files[i].getInputStream(),new File(path,fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("上传出错");
                }
                Image image = new Image();
                image.setImageUrl(file);
                image.setProductId(product.getId());
                goodService.addImage(image);
                fileName =UUID.randomUUID().toString()+".jpg";
                file = "http://121.42.190.17/Shop/"+fileName;
            }
        }

        return "redirect:/BackStage/ListProduct";
    }


    @RequestMapping(value = "/BackStage/ListOrders", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String listOrders(Model model,HttpServletRequest request){
        List<OrderDetailPojo> orderses;
        if(request.getParameter("status")!=null){
            orderses = orderService.findByStatus(Integer.valueOf(request.getParameter("status")));
        }else {
            orderses = orderService.findAll();
        }
        model.addAttribute("orderPojos",orderses);
        return "backStage/Orders/listOrders";
    }

    @RequestMapping(value="/loginTerrace",method = RequestMethod.GET)
    public String loginTerrace(){
        return "backStage/login";
    }


    @RequestMapping(value="/logOutTerrace",method = RequestMethod.GET)
    public String logOutTerrace(HttpSession session){
        session.setAttribute("loginAdmin",null);
        return "backStage/login";
    }
    @RequestMapping(value="/loginTerrace",method = RequestMethod.POST)
    public String loginTerrace(User user,HttpSession session){
        UserDTO userDTO = userService.loginUser(user.getPhone(),user.getPassword());
        if(userDTO.getUser()!=null){
            if(userDTO.getUser().getType()==1)
            session.setAttribute("loginAdmin",userDTO.getUser());
            return "redirect:/BackStage/ListProduct";
        }
        return "backStage/login";
    }

    @RequestMapping(value="BackStage/orderDetail",method = RequestMethod.GET)
    public String ordersDetail(Model model,int id){
        OrderDetailPojo orderDetailPojo = orderService.findOrderDetail(id);
        model.addAttribute("orderPojo",orderDetailPojo);
        return "backStage/Orders/orderDetail";
    }


    @RequestMapping(value="BackStage/SendGood",method = RequestMethod.GET)
    public String sendGood(int id){
        Orders orders = orderService.findOrder(id);
        orders.setStatus(2);
        String data = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data);
        orders.setSentDate(simpleDateFormat.format(new Date()));
        orderService.updateOrders(orders);
        return "redirect:/BackStage/orderDetail?id="+id;
    }


    @RequestMapping(value="BackStage/deleteProduct",method = RequestMethod.GET)
    public String deleteGood(int id){
        Product product = goodService.findProductById(id);
        goodService.deleteProduct(product);
        return "redirect:/BackStage/ListProduct";
    }

    @RequestMapping(value = "uploadFile",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addGoodDown(@RequestParam("files")MultipartFile files){
        String fileNames = UUID.randomUUID().toString()+".jpg";
        String path= "D:/";
        try {
            FileUtils.copyInputStreamToFile(files.getInputStream(),new File(path,fileNames));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/indexCarousel";
    }

    @RequestMapping(value = "addFile")
    @ResponseBody
    public String addFile(String fileName,String fileStream){
        try {
            BASE64Decoder base64Encoder = new BASE64Decoder();
            byte[] bytes = base64Encoder.decodeBuffer(fileStream);
            if(StringUtils.isEmpty(fileName)){
                fileName = "test.jpg";
            }
            File file = new File("D:/"+fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void test() throws IOException {
        Jedis jedis = new Jedis("192.168.132.129");
        String name =jedis.get("name");
        System.out.println(name);
    }

}
