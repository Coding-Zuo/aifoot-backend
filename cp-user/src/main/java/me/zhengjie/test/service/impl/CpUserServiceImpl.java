package me.zhengjie.test.service.impl;

import me.zhengjie.test.domain.CpUser;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.test.repository.CpUserRepository;
import me.zhengjie.test.service.CpUserService;
import me.zhengjie.test.service.dto.CpUserDto;
import me.zhengjie.test.service.dto.CpUserQueryCriteria;
import me.zhengjie.test.service.mapper.CpUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author zuo
* @date 2020-04-27
*/
@Service
//@CacheConfig(cacheNames = "cpUser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CpUserServiceImpl implements CpUserService {

    private final CpUserRepository cpUserRepository;

    private final CpUserMapper cpUserMapper;

    public CpUserServiceImpl(CpUserRepository cpUserRepository, CpUserMapper cpUserMapper) {
        this.cpUserRepository = cpUserRepository;
        this.cpUserMapper = cpUserMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(CpUserQueryCriteria criteria, Pageable pageable){
        Page<CpUser> page = cpUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(cpUserMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<CpUserDto> queryAll(CpUserQueryCriteria criteria){
        return cpUserMapper.toDto(cpUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public CpUserDto findById(Integer id) {
        CpUser cpUser = cpUserRepository.findById(id).orElseGet(CpUser::new);
        ValidationUtil.isNull(cpUser.getId(),"CpUser","id",id);
        return cpUserMapper.toDto(cpUser);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CpUserDto create(CpUser resources) {
        return cpUserMapper.toDto(cpUserRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(CpUser resources) {
        CpUser cpUser = cpUserRepository.findById(resources.getId()).orElseGet(CpUser::new);
        ValidationUtil.isNull( cpUser.getId(),"CpUser","id",resources.getId());
        cpUser.copy(resources);
        cpUserRepository.save(cpUser);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            cpUserRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CpUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CpUserDto cpUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("微信openid", cpUser.getOpenId());
            map.put("昵称", cpUser.getNickname());
            map.put("头像", cpUser.getNickpic());
            map.put("性别", cpUser.getSex());
            map.put("电话号", cpUser.getPhone());
            map.put("语言", cpUser.getLanguage());
            map.put("头像", cpUser.getHeadimgurl());
            map.put("国家", cpUser.getCountry());
            map.put("省份", cpUser.getProvince());
            map.put("城市", cpUser.getCity());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
