package com.cyl.manager.aws.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.aws.domain.ActMemberCoupon;
import com.cyl.manager.aws.pojo.vo.ActMemberCouponVO;
import java.util.List;
/**
 * 用户领券记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface ActMemberCouponConvert  {

    List<ActMemberCouponVO> dos2vos(List<ActMemberCoupon> list);
}
