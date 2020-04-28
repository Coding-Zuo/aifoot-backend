package me.zhengjie.modules.test.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author zuo
* @date 2020-04-28
*/
@Data
public class ZuoTestDto implements Serializable {

    /** id */
    private Integer id;

    /** 姓名 */
    private String name;
}