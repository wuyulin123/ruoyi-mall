package com.cyl.manager.aws.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户领券记录 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="用户领券记录 查询 对象")
@Data
public class ActMemberCouponQuery {
    @ApiModelProperty("活动id 精确匹配")
    private Long couponActivityId;

    @ApiModelProperty("用户id 精确匹配")
    private Long memberId;

    @ApiModelProperty("活动名称 精确匹配")
    private String title;

    @ApiModelProperty("使用范围  1全场通用 2指定商品可用 3指定商品不可用 精确匹配")
    private Integer useScope;

    @ApiModelProperty("商品id集合，逗号分隔 精确匹配")
    private String productIds;

    @ApiModelProperty("优惠券金额 精确匹配")
    private BigDecimal couponAmount;

    @ApiModelProperty("最低消费金额 精确匹配")
    private BigDecimal minAmount;

    @ApiModelProperty("要兑换的积分 精确匹配")
    private BigDecimal useIntegral;

    @ApiModelProperty("1免费兑换  2积分兑换 精确匹配")
    private Integer couponType;

    @ApiModelProperty("券开始时间 精确匹配")
    private LocalDateTime beginTime;

    @ApiModelProperty("券结束时间 精确匹配")
    private LocalDateTime endTime;

    @ApiModelProperty("0未使用  1已使用 精确匹配")
    private Integer useStatus;

    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @ApiModelProperty("使用时间 精确匹配")
    private LocalDateTime useTime;

}
