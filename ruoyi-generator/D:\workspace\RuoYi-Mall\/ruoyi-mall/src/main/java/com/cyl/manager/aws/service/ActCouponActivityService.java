package com.cyl.manager.aws.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.aws.mapper.ActCouponActivityMapper;
import com.cyl.manager.aws.domain.ActCouponActivity;
import com.cyl.manager.aws.pojo.query.ActCouponActivityQuery;

/**
 * 优惠券活动表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class ActCouponActivityService {
    @Autowired
    private ActCouponActivityMapper actCouponActivityMapper;

    /**
     * 查询优惠券活动表
     *
     * @param id 优惠券活动表主键
     * @return 优惠券活动表
     */
    public ActCouponActivity selectById(Long id) {
        return actCouponActivityMapper.selectById(id);
    }

    /**
     * 查询优惠券活动表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 优惠券活动表
     */
    public List<ActCouponActivity> selectList(ActCouponActivityQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ActCouponActivity> qw = new QueryWrapper<>();
        String title = query.getTitle();
        if (!StringUtils.isEmpty(title)) {
            qw.eq("title", title);
        }
        Integer useScope = query.getUseScope();
        if (useScope != null) {
            qw.eq("use_scope", useScope);
        }
        String productIds = query.getProductIds();
        if (!StringUtils.isEmpty(productIds)) {
            qw.eq("product_ids", productIds);
        }
        Integer totalCount = query.getTotalCount();
        if (totalCount != null) {
            qw.eq("total_count", totalCount);
        }
        Integer leftCount = query.getLeftCount();
        if (leftCount != null) {
            qw.eq("left_count", leftCount);
        }
        Integer userLimit = query.getUserLimit();
        if (userLimit != null) {
            qw.eq("user_limit", userLimit);
        }
        BigDecimal couponAmount = query.getCouponAmount();
        if (couponAmount != null) {
            qw.eq("coupon_amount", couponAmount);
        }
        BigDecimal minAmount = query.getMinAmount();
        if (minAmount != null) {
            qw.eq("min_amount", minAmount);
        }
        BigDecimal useIntegral = query.getUseIntegral();
        if (useIntegral != null) {
            qw.eq("use_integral", useIntegral);
        }
        Integer couponType = query.getCouponType();
        if (couponType != null) {
            qw.eq("coupon_type", couponType);
        }
        LocalDateTime beginTime = query.getBeginTime();
        if (beginTime != null) {
            qw.eq("begin_time", beginTime);
        }
        LocalDateTime endTime = query.getEndTime();
        if (endTime != null) {
            qw.eq("end_time", endTime);
        }
        return actCouponActivityMapper.selectList(qw);
    }

    /**
     * 新增优惠券活动表
     *
     * @param actCouponActivity 优惠券活动表
     * @return 结果
     */
    public int insert(ActCouponActivity actCouponActivity) {
        actCouponActivity.setCreateTime(LocalDateTime.now());
        return actCouponActivityMapper.insert(actCouponActivity);
    }

    /**
     * 修改优惠券活动表
     *
     * @param actCouponActivity 优惠券活动表
     * @return 结果
     */
    public int update(ActCouponActivity actCouponActivity) {
        return actCouponActivityMapper.updateById(actCouponActivity);
    }

    /**
     * 删除优惠券活动表信息
     *
     * @param id 优惠券活动表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return actCouponActivityMapper.deleteById(id);
    }
}
