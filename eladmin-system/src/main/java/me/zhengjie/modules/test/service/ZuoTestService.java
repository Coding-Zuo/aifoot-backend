package me.zhengjie.modules.test.service;

import me.zhengjie.modules.test.domain.ZuoTest;
import me.zhengjie.modules.test.service.dto.ZuoTestDto;
import me.zhengjie.modules.test.service.dto.ZuoTestQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zuo
* @date 2020-04-28
*/
public interface ZuoTestService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZuoTestQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZuoTestDto>
    */
    List<ZuoTestDto> queryAll(ZuoTestQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return ZuoTestDto
     */
    ZuoTestDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return ZuoTestDto
    */
    ZuoTestDto create(ZuoTest resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(ZuoTest resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZuoTestDto> all, HttpServletResponse response) throws IOException;
}