package me.zhengjie.test.service;

import me.zhengjie.test.domain.CpUser;
import me.zhengjie.test.service.dto.CpUserDto;
import me.zhengjie.test.service.dto.CpUserQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zuo
* @date 2020-04-27
*/
public interface CpUserService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(CpUserQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CpUserDto>
    */
    List<CpUserDto> queryAll(CpUserQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CpUserDto
     */
    CpUserDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return CpUserDto
    */
    CpUserDto create(CpUser resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(CpUser resources);

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
    void download(List<CpUserDto> all, HttpServletResponse response) throws IOException;
}