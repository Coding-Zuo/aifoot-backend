package me.zhengjie.test.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author zuo
* @date 2020-04-27
*/
@Entity
@Data
@Table(name="cp_user")
public class CpUser implements Serializable {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 微信openid */
    @Column(name = "open_id",nullable = false)
    @NotBlank
    private String openId;

    /** 昵称 */
    @Column(name = "nickname")
    private String nickname;

    /** 头像 */
    @Column(name = "nickpic")
    private String nickpic;

    /** 性别 */
    @Column(name = "sex")
    private Integer sex;

    /** 电话号 */
    @Column(name = "phone")
    private String phone;

    /** 语言 */
    @Column(name = "language")
    private String language;

    /** 头像 */
    @Column(name = "headimgurl")
    private String headimgurl;

    /** 国家 */
    @Column(name = "country")
    private String country;

    /** 省份 */
    @Column(name = "province")
    private String province;

    /** 城市 */
    @Column(name = "city")
    private String city;

    public void copy(CpUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}