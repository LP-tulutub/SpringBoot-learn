package com.lp.dao;

import com.lp.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 参数一 T :当前需要映射的实体
 * 参数二 ID :当前映射的实体中的OID的类型
 *
 */
public interface TestRepository extends JpaRepository<Users,Integer> {

}
