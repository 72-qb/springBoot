package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.test.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 20:14
 */
public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    Page<Student> getStudentsBySearchVo(SearchVo searchVo);

    List<Student> getStudentsByStudentName(String studentName);

    List<Student> getStudentsByStudentNameLike(String studentName);

    List<Student> getStudentsTop2ByStudentNameLike(String studentName);

    List<Student> getStudentsByParams(String studentName, int cardId);
}
