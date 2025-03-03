package com.cyl.manager.aws.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.aws.domain.ActCouponActivity;
import com.cyl.manager.aws.pojo.vo.ActCouponActivityVO;
import java.util.List;
/**
 * 优惠券活动表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface ActCouponActivityConvert  {

    List<ActCouponActivityVO> dos2vos(List<ActCouponActivity> list);
}
