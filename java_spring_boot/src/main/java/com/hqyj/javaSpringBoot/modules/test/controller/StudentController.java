package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.pojo.Student;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 20:17
 */
@RestController
@RequestMapping("/sc")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
    * localhost/sc/student   --------->post方法
    * {"studentName":"小明","studentCard":{"cardId":"1"}}
    * */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }


    /*
    * localhost/sc/student/6  ------->get方法
    *
    * */
    @GetMapping(value = "/students/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId) {
        return studentService.getStudentByStudentId(studentId);
    }

    /*
    * localhost/sc/student ------------post方法
    *{"currentPage":"1","pageSize":"5","keyWord":"hu","orderBy":"studentName","sort":"desc"}
    * */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
        return studentService.getStudentsBySearchVo(searchVo);
    }

    /*
    * localhost/sc/students?studentName=向小仙1
    * */
    @GetMapping(value = "/students")
    public List<Student> getStudentsByStudentName(@RequestParam String studentName){
        return studentService.getStudentsByStudentName(studentName);
    }

    /*
     * localhost/sc/students1?studentName=向
     * */
    @GetMapping(value = "/students1")
    public List<Student> getStudentsByStudentNameLike(@RequestParam String studentName){
        return studentService.getStudentsByStudentNameLike(studentName);
    }

    /*
     * localhost/sc/students2?studentName=向
     * */
    @GetMapping(value = "/students2")
    public List<Student> getStudentsTop2ByStudentNameLike(@RequestParam String studentName){
        return studentService.getStudentsTop2ByStudentNameLike(studentName);
    }

    /*
     * localhost/sc/students3?studentName=向&cardId=1
     * */
    @GetMapping(value = "/students3")
    public List<Student> getStudentsByParams(@RequestParam String studentName,@RequestParam int cardId){
        return studentService.getStudentsByParams(studentName,cardId);
    }
}
