package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.pojo.City;
import com.hqyj.javaSpringBoot.modules.test.pojo.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import com.hqyj.javaSpringBoot.modules.test.vo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/10 12:53
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Value("${server.port}")
    private String port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private Application application;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    /*
    * http://localhost:8085/test/logTest
    * */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is a log24342423423";
    }

    /*
     * http://localhost:8085/test/config
     * */
    @GetMapping("/config")
    @ResponseBody
    public String config() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("********")
                .append(name).append("********")
                .append(age).append("********")
                .append(desc).append("********")
                .append(random).append("********").append("<br>")
                .append(application.getPort()).append("********")
                .append(application.getName()).append("********")
                .append(application.getAge()).append("********")
                .append(application.getDesc()).append("********")
                .append(application.getRandom()).append("********");
        return sb.toString();
    }

    /**
     * http://localhost:8080/test/test01
     * get请求
     */
    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "hello spring-boot";
    }

    @GetMapping("/index")
    public String testThymeleaf(ModelMap map){
        int countryId = 522;
        List<City> cities = cityService.getCityByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);
        map.addAttribute("thymeleafTitle","我来替换内容");
        map.addAttribute("checked", true);
        map.addAttribute("currentNumber", 99);
        map.addAttribute("changeType", "checkbox");
        map.addAttribute("baiduUrl", "/test/log");
        map.addAttribute("city", cities.get(0));
        map.addAttribute("shopLogo",
                "/img/1111.png");
        map.addAttribute("country", country);
        map.addAttribute("cities", cities);
        map.addAttribute("updateCityUrl","/cc/city");
/*        map.addAttribute("template","/test/index");*/
        return "index";
    }

    /*
    * localhost/test/testDesc?paramKey=fuck
    * */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request, @RequestParam(value = "paramKey") String paramValue){
        String paramValue2=request.getParameter("paramKey");
        return "This is test module desc***"+paramValue+"=="+paramValue2;
    }

    /*
    * localhost/test/file
    *
    * */
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select file;");
            return "redirect:/test/index";
        }
        try {
            String filePath="D:\\upload\\"+file.getOriginalFilename();
            File destFile=new File(filePath);
            file.transferTo(destFile);
            redirectAttributes.addFlashAttribute("message","upload is success");
        }catch (IOException ie){
            ie.printStackTrace();
            redirectAttributes.addFlashAttribute("message","upload file failed");
        }

        return "redirect:/test/index";
    }

    /*
    * localhost/test/files
    * */
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes){
        boolean empty=true;
       try {
           for (MultipartFile file : files) {
               if(file.isEmpty()){
                   continue;
               }
               String destFilePath="D:\\upload\\"+file.getOriginalFilename();
               File destFile=new File(destFilePath);
               file.transferTo(destFile);
               empty=false;
           }
           if (empty){
               redirectAttributes.addFlashAttribute("message","Please select file;");
           }else {
               redirectAttributes.addFlashAttribute("message","upload files success");
           }
       }catch (IOException e){
           e.printStackTrace();
           redirectAttributes.addFlashAttribute("message","upload files failed");
       }
        return "redirect:/test/index";
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
        Resource resource= null;
        try {
            resource = new UrlResource(Paths.get("D:\\upload\\"+fileName).toUri());
            /*exists存在,isReadable可读*/
            if (resource.exists() && resource.isReadable()){
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "application/octet-steam")
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                String.format("attachment; filename=\"%s\"",resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
