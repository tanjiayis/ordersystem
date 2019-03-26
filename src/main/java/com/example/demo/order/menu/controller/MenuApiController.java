package com.example.demo.order.menu.controller;

import com.example.demo.controller.BaseApiController;
import com.example.demo.data.CommonResult;
import com.example.demo.exception.BusinessException;
import com.example.demo.order.menu.dto.AddMenuDto;
import com.example.demo.order.menu.dto.AddTypeDto;
import com.example.demo.order.menu.dto.DetailDto;
import com.example.demo.order.menu.dto.UploadDto;
import com.example.demo.order.menu.pojo.Menu;
import com.example.demo.order.menu.pojo.MenuType;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.order.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
@RestController
@RequestMapping("/api/admin/menu")
public class MenuApiController extends BaseApiController {

    @Autowired
    private MenuService menuService;
    @RequestMapping("/add")
    public CommonResult addMenu(@Valid AddMenuDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.addMenu(dto.getTypeId(), dto.getName(), dto.getPrice(), dto.getRemark(), dto.getImageName());
        return new CommonResult();
    }
    @RequestMapping("/detail")
    public CommonResult detail(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        Menu menu = menuService.findMenu(dto.getId());
        return new CommonResult(menu);
    }
    @RequestMapping("/edit")
    public CommonResult editMenu(@Valid AddMenuDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.editMenu(dto.getMenuId(), dto.getTypeId(), dto.getName(), dto.getPrice(), dto.getRemark(), dto.getImageName());
        return new CommonResult();
    }
    @RequestMapping("/delete")
    public CommonResult deleteMenu(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.deleteMenu(dto.getId());
        return new CommonResult();
    }
    @RequestMapping("/type/detail")
    public CommonResult detailType(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        MenuType menuType = menuService.findType(dto.getId());
        return new CommonResult(menuType);
    }
    @RequestMapping("/type/add")
    public CommonResult addType(@Valid AddTypeDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.addType(dto.getTypeName());
        return new CommonResult();
    }
    @RequestMapping("/type/edit")
    public CommonResult editType(@Valid AddTypeDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.editType(dto.getId(), dto.getTypeName());
        return new CommonResult();
    }
    @RequestMapping("/type/delete")
    public CommonResult deleteType(@Valid DetailDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new IllegalArgumentException(bindingResult);
        menuService.deleteType(dto.getId());
        return new CommonResult();
    }
//    @RequestMapping("/uploader")
//    public CommonResult uploader(HttpServletResponse response, HttpServletRequest request, UploadDto dto,
//                                 @RequestParam(value = "name", required = false) String name,
//                                 @RequestParam(value = "uuid", required = false) String uuid,
//                                 @RequestParam(value = "chunk", defaultValue = "0") int chunk,
//                                 @RequestParam(value = "chunks", defaultValue = "1") int chunks,
//                                 @RequestHeader(value = "adddate", required = false) Integer addDate,
//                                 @RequestParam(value = "file", required = false) MultipartFile file){
//
//        File targetFile=null;
//        String url = "";
//        int code=1;
//        String fileName = file.getOriginalFilename();
//        if(fileName!=null&&fileName!=""){
////            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
////            System.out.println(returnUrl);
////            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
////            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
//
//            //先判断文件是否存在
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
////            String fileAdd = sdf.format(new Date());
//            //获取文件夹路径
////            File file1 =new File(path+"/"+fileAdd);
//            //如果文件夹不存在则创建
////            if(!file1 .exists()  && !file1 .isDirectory()){
////                file1 .mkdir();
//            }
//            //将图片存入文件夹
//            targetFile = new File("D:\\My_work\\ordersystem\\src\\main\\resources\\static\\images\\menus", fileName);
//            try {
//                //将上传的文件写到服务器上指定的文件。
//                file.transferTo(targetFile);
////                url=returnUrl+fileAdd+"/"+fileName;
//                code=0;
////                result.setCode(code);
////                result.setMessage("图片上传成功");
////                map.put("url", url);
////                result.setResult(map);
//                return new CommonResult(url);
//            } catch (Exception e) {
//                e.printStackTrace();
//               throw new BusinessException("系统异常！");
//            }
//        }
//        return new CommonResult();
//    }
    @RequestMapping("/uploader")
    public CommonResult uploader2(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "uuid", required = false) String uuid,
                                 @RequestParam(value = "chunk", defaultValue = "0") int chunk,
                                 @RequestParam(value = "chunks", defaultValue = "1") int chunks,
                                 @RequestHeader(value = "adddate", required = false) Integer addDate,
                                 @RequestParam(value = "file", required = false) MultipartFile file){
        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//        String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//        fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
        String serverpath= "";
        String path = "";
        try {
            serverpath = ResourceUtils.getURL("classpath:static/images/menus").getPath().replace("%20"," ").replace('/', '\\');
            path=serverpath.substring(1);
            File targetFile = new File(path, fileName);
            file.transferTo(targetFile);
//            menuService.addMenuImg(fileName);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new BusinessException("图片上传失败！");
        }
        return new CommonResult();
    }
}
