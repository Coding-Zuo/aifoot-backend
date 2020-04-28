package me.zhengjie.modules.test.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.test.domain.ZuoTest;
import me.zhengjie.modules.test.service.dto.ZuoTestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zuo
* @date 2020-04-28
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZuoTestMapper extends BaseMapper<ZuoTestDto, ZuoTest> {

}