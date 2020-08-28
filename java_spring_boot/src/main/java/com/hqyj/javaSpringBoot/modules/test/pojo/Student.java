package com.hqyj.javaSpringBoot.modules.test.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 18:43
 */
@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime createDate;

    @OneToOne(targetEntity = Card.class,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card studentCard;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Card getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(Card studentCard) {
        this.studentCard = studentCard;
    }

    @ManyToMany(mappedBy = "students",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private List<Clazz> clazzes;

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
