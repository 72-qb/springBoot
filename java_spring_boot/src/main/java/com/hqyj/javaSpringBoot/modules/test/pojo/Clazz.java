package com.hqyj.javaSpringBoot.modules.test.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 18:42
 */
@Entity
@Table(name = "t_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clazzId;

    private String schoolName;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    /*insertable、updatable：标识该属性是否参与插入和更新插入*/
    @JoinColumn(name = "school_id",insertable = false,updatable = false)
    @JsonIgnore
    private School school;

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(name = "t_clazz_student",
    joinColumns = @JoinColumn(name = "clazz_id"),
    inverseJoinColumns = @JoinColumn(name = "syudent_id"))
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
