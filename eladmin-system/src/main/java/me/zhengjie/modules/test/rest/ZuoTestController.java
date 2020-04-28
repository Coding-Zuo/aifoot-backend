package me.zhengjie.modules.test.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.test.domain.ZuoTest;
import me.zhengjie.modules.test.service.ZuoTestService;
import me.zhengjie.modules.test.service.dto.ZuoTestQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zuo
* @date 2020-04-28
*/
@Api(tags = "z测试生成代码管理")
@RestController
@RequestMapping("/api/zuoTest")
public class ZuoTestController {

    private final ZuoTestService zuoTestService;

    public ZuoTestController(ZuoTestService zuoTestService) {
        this.zuoTestService = zuoTestService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('zuoTest:list')")
    public void download(HttpServletResponse response, ZuoTestQueryCriteria criteria) throws IOException {
        zuoTestService.download(zuoTestService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询z测试生成代码")
    @ApiOperation("查询z测试生成代码")
    @PreAuthorize("@el.check('zuoTest:list')")
    public ResponseEntity<Object> getZuoTests(ZuoTestQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zuoTestService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增z测试生成代码")
    @ApiOperation("新增z测试生成代码")
    @PreAuthorize("@el.check('zuoTest:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZuoTest resources){
        return new ResponseEntity<>(zuoTestService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改z测试生成代码")
    @ApiOperation("修改z测试生成代码")
    @PreAuthorize("@el.check('zuoTest:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZuoTest resources){
        zuoTestService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除z测试生成代码")
    @ApiOperation("删除z测试生成代码")
    @PreAuthorize("@el.check('zuoTest:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        zuoTestService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}