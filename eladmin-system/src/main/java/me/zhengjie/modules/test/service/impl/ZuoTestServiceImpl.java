package me.zhengjie.modules.test.service.impl;

import me.zhengjie.modules.test.domain.ZuoTest;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.test.repository.ZuoTestRepository;
import me.zhengjie.modules.test.service.ZuoTestService;
import me.zhengjie.modules.test.service.dto.ZuoTestDto;
import me.zhengjie.modules.test.service.dto.ZuoTestQueryCriteria;
import me.zhengjie.modules.test.service.mapper.ZuoTestMapper;
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
* @date 2020-04-28
*/
@Service
//@CacheConfig(cacheNames = "zuoTest")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZuoTestServiceImpl implements ZuoTestService {

    private final ZuoTestRepository zuoTestRepository;

    private final ZuoTestMapper zuoTestMapper;

    public ZuoTestServiceImpl(ZuoTestRepository zuoTestRepository, ZuoTestMapper zuoTestMapper) {
        this.zuoTestRepository = zuoTestRepository;
        this.zuoTestMapper = zuoTestMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(ZuoTestQueryCriteria criteria, Pageable pageable){
        Page<ZuoTest> page = zuoTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(zuoTestMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<ZuoTestDto> queryAll(ZuoTestQueryCriteria criteria){
        return zuoTestMapper.toDto(zuoTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public ZuoTestDto findById(Integer id) {
        ZuoTest zuoTest = zuoTestRepository.findById(id).orElseGet(ZuoTest::new);
        ValidationUtil.isNull(zuoTest.getId(),"ZuoTest","id",id);
        return zuoTestMapper.toDto(zuoTest);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public ZuoTestDto create(ZuoTest resources) {
        return zuoTestMapper.toDto(zuoTestRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(ZuoTest resources) {
        ZuoTest zuoTest = zuoTestRepository.findById(resources.getId()).orElseGet(ZuoTest::new);
        ValidationUtil.isNull( zuoTest.getId(),"ZuoTest","id",resources.getId());
        zuoTest.copy(resources);
        zuoTestRepository.save(zuoTest);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            zuoTestRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ZuoTestDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZuoTestDto zuoTest : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("姓名", zuoTest.getName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
