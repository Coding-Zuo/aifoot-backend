package me.zhengjie.modules.test.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author zuo
* @date 2020-04-28
*/
@Entity
@Data
@Table(name="zuo_test")
public class ZuoTest implements Serializable {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    public void copy(ZuoTest source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}