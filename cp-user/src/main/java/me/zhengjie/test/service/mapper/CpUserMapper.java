package me.zhengjie.test.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.test.domain.CpUser;
import me.zhengjie.test.service.dto.CpUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zuo
* @date 2020-04-27
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CpUserMapper extends BaseMapper<CpUserDto, CpUser> {

}