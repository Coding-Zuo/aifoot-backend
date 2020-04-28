package me.zhengjie.test.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.test.domain.CpUser;
import me.zhengjie.test.service.CpUserService;
import me.zhengjie.test.service.dto.CpUserQueryCriteria;
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
* @date 2020-04-27
*/
@Api(tags = "测试生成管理")
@RestController
@RequestMapping("/api/cpUser")
public class CpUserController {

    private final CpUserService cpUserService;

    public CpUserController(CpUserService cpUserService) {
        this.cpUserService = cpUserService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('cpUser:list')")
    public void download(HttpServletResponse response, CpUserQueryCriteria criteria) throws IOException {
        cpUserService.download(cpUserService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询测试生成")
    @ApiOperation("查询测试生成")
    @PreAuthorize("@el.check('cpUser:list')")
    public ResponseEntity<Object> getCpUsers(CpUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(cpUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增测试生成")
    @ApiOperation("新增测试生成")
    @PreAuthorize("@el.check('cpUser:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CpUser resources){
        return new ResponseEntity<>(cpUserService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改测试生成")
    @ApiOperation("修改测试生成")
    @PreAuthorize("@el.check('cpUser:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CpUser resources){
        cpUserService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除测试生成")
    @ApiOperation("删除测试生成")
    @PreAuthorize("@el.check('cpUser:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        cpUserService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}