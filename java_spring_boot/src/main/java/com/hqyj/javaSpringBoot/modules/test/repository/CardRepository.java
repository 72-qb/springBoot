package com.hqyj.javaSpringBoot.modules.test.repository;

import com.hqyj.javaSpringBoot.modules.test.pojo.Card;
import com.hqyj.javaSpringBoot.modules.test.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/12 19:58
 */
@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

}
