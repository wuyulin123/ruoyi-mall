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
import com.cyl.manager.aws.convert.ActCouponActivityConvert;
import com.cyl.manager.aws.domain.ActCouponActivity;
import com.cyl.manager.aws.pojo.query.ActCouponActivityQuery;
import com.cyl.manager.aws.service.ActCouponActivityService;
import com.cyl.manager.aws.pojo.vo.ActCouponActivityVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 优惠券活动表Controller
 * 
 * @author zcc
 * @date 2025-03-03
 */
@Api(description ="优惠券活动表接口列表")
@RestController
@RequestMapping("/aws/actCouponActivity")
public class ActCouponActivityController extends BaseController {
    @Autowired
    private ActCouponActivityService service;
    @Autowired
    private ActCouponActivityConvert convert;

    @ApiOperation("查询优惠券活动表列表")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ActCouponActivity>> list(@RequestBody ActCouponActivityQuery query, Pageable page) {
        List<ActCouponActivity> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出优惠券活动表列表")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:export')")
    @Log(title = "优惠券活动表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ActCouponActivityQuery query) {
        List<ActCouponActivity> list = service.selectList(query, null);
        ExcelUtil<ActCouponActivityVO> util = new ExcelUtil<>(ActCouponActivityVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "优惠券活动表数据"));
    }

    @ApiOperation("获取优惠券活动表详细信息")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ActCouponActivity> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增优惠券活动表")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:add')")
    @Log(title = "优惠券活动表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ActCouponActivity actCouponActivity) {
        return ResponseEntity.ok(service.insert(actCouponActivity));
    }

    @ApiOperation("修改优惠券活动表")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:edit')")
    @Log(title = "优惠券活动表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ActCouponActivity actCouponActivity) {
        return ResponseEntity.ok(service.update(actCouponActivity));
    }

    @ApiOperation("删除优惠券活动表")
    @PreAuthorize("@ss.hasPermi('aws:actCouponActivity:remove')")
    @Log(title = "优惠券活动表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
