package com.hqyj.javaSpringBoot.modules.test.pojo;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 18:43
 */
@Entity
@Table(name = "t_school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private int schoolName;
    /*该注解表是该参数不参与映射*/
    @Transient
    private String region;

    private String schoolok;

    @Transient
    private String schoolno;

    @OneToMany(mappedBy = "school",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private List<Clazz> clazzes;

    public int getSchoolId() {

        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(int schoolName) {
        this.schoolName = schoolName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
