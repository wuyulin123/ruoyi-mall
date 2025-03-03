package com.cyl.manager.aws.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.manager.aws.domain.ActCouponActivity;

/**
 * 优惠券活动表Mapper接口
 * 
 * @author zcc
 */
public interface ActCouponActivityMapper extends BaseMapper<ActCouponActivity> {
    /**
     * 查询优惠券活动表列表
     *
     * @param actCouponActivity 优惠券活动表
     * @return 优惠券活动表集合
     */
    List<ActCouponActivity> selectByEntity(ActCouponActivity actCouponActivity);
}
