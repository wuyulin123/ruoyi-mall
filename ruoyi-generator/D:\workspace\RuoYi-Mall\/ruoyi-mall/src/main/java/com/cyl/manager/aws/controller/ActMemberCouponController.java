package com.cyl.manager.aws.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.manager.aws.convert.ActMemberCouponConvert;
import com.cyl.manager.aws.domain.ActMemberCoupon;
import com.cyl.manager.aws.pojo.query.ActMemberCouponQuery;
import com.cyl.manager.aws.service.ActMemberCouponService;
import com.cyl.manager.aws.pojo.vo.ActMemberCouponVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 用户领券记录Controller
 * 
 * @author zcc
 * @date 2025-03-03
 */
@Api(description ="用户领券记录接口列表")
@RestController
@RequestMapping("/aws/actMemberCoupon")
public class ActMemberCouponController extends BaseController {
    @Autowired
    private ActMemberCouponService service;
    @Autowired
    private ActMemberCouponConvert convert;

    @ApiOperation("查询用户领券记录列表")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ActMemberCoupon>> list(@RequestBody ActMemberCouponQuery query, Pageable page) {
        List<ActMemberCoupon> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出用户领券记录列表")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:export')")
    @Log(title = "用户领券记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ActMemberCouponQuery query) {
        List<ActMemberCoupon> list = service.selectList(query, null);
        ExcelUtil<ActMemberCouponVO> util = new ExcelUtil<>(ActMemberCouponVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "用户领券记录数据"));
    }

    @ApiOperation("获取用户领券记录详细信息")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ActMemberCoupon> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增用户领券记录")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:add')")
    @Log(title = "用户领券记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ActMemberCoupon actMemberCoupon) {
        return ResponseEntity.ok(service.insert(actMemberCoupon));
    }

    @ApiOperation("修改用户领券记录")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:edit')")
    @Log(title = "用户领券记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ActMemberCoupon actMemberCoupon) {
        return ResponseEntity.ok(service.update(actMemberCoupon));
    }

    @ApiOperation("删除用户领券记录")
    @PreAuthorize("@ss.hasPermi('aws:actMemberCoupon:remove')")
    @Log(title = "用户领券记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
