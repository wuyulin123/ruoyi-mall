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
import com.cyl.manager.aws.mapper.ActMemberCouponMapper;
import com.cyl.manager.aws.domain.ActMemberCoupon;
import com.cyl.manager.aws.pojo.query.ActMemberCouponQuery;

/**
 * 用户领券记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class ActMemberCouponService {
    @Autowired
    private ActMemberCouponMapper actMemberCouponMapper;

    /**
     * 查询用户领券记录
     *
     * @param id 用户领券记录主键
     * @return 用户领券记录
     */
    public ActMemberCoupon selectById(Long id) {
        return actMemberCouponMapper.selectById(id);
    }

    /**
     * 查询用户领券记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 用户领券记录
     */
    public List<ActMemberCoupon> selectList(ActMemberCouponQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ActMemberCoupon> qw = new QueryWrapper<>();
        Long couponActivityId = query.getCouponActivityId();
        if (couponActivityId != null) {
            qw.eq("coupon_activity_id", couponActivityId);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
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
        Integer useStatus = query.getUseStatus();
        if (useStatus != null) {
            qw.eq("use_status", useStatus);
        }
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        LocalDateTime useTime = query.getUseTime();
        if (useTime != null) {
            qw.eq("use_time", useTime);
        }
        return actMemberCouponMapper.selectList(qw);
    }

    /**
     * 新增用户领券记录
     *
     * @param actMemberCoupon 用户领券记录
     * @return 结果
     */
    public int insert(ActMemberCoupon actMemberCoupon) {
        actMemberCoupon.setCreateTime(LocalDateTime.now());
        return actMemberCouponMapper.insert(actMemberCoupon);
    }

    /**
     * 修改用户领券记录
     *
     * @param actMemberCoupon 用户领券记录
     * @return 结果
     */
    public int update(ActMemberCoupon actMemberCoupon) {
        return actMemberCouponMapper.updateById(actMemberCoupon);
    }

    /**
     * 删除用户领券记录信息
     *
     * @param id 用户领券记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return actMemberCouponMapper.deleteById(id);
    }
}
