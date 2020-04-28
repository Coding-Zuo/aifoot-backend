package me.zhengjie.test.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author zuo
* @date 2020-04-27
*/
@Data
public class CpUserQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String openId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String nickname;
}