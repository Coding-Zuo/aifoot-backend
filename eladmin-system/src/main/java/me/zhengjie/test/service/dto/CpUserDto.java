package me.zhengjie.test.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author zuo
* @date 2020-04-27
*/
@Data
public class CpUserDto implements Serializable {

    /** id */
    private Integer id;

    /** 微信openid */
    private String openId;

    /** 昵称 */
    private String nickname;

    /** 头像 */
    private String nickpic;

    /** 性别 */
    private Integer sex;

    /** 电话号 */
    private String phone;

    /** 语言 */
    private String language;

    /** 头像 */
    private String headimgurl;

    /** 国家 */
    private String country;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;
}