package com.cyl.manager.aws.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.manager.aws.domain.ActMemberCoupon;

/**
 * 用户领券记录Mapper接口
 * 
 * @author zcc
 */
public interface ActMemberCouponMapper extends BaseMapper<ActMemberCoupon> {
    /**
     * 查询用户领券记录列表
     *
     * @param actMemberCoupon 用户领券记录
     * @return 用户领券记录集合
     */
    List<ActMemberCoupon> selectByEntity(ActMemberCoupon actMemberCoupon);
}
