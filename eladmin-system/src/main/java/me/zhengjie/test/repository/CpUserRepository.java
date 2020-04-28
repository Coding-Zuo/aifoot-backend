package me.zhengjie.test.repository;

import me.zhengjie.test.domain.CpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zuo
* @date 2020-04-27
*/
public interface CpUserRepository extends JpaRepository<CpUser, Integer>, JpaSpecificationExecutor<CpUser> {
}