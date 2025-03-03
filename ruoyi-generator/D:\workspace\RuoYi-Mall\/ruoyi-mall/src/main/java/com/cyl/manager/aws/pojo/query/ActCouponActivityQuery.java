package com.cyl.manager.aws.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠券活动表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="优惠券活动表 查询 对象")
@Data
public class ActCouponActivityQuery {
    @ApiModelProperty("活动名称 精确匹配")
    private String title;

    @ApiModelProperty("使用范围  1全场通用 2指定商品可用 3指定商品不可用 精确匹配")
    private Integer useScope;

    @ApiModelProperty("商品id集合，逗号分隔 精确匹配")
    private String productIds;

    @ApiModelProperty("发行总数 精确匹配")
    private Integer totalCount;

    @ApiModelProperty("剩余总数 精确匹配")
    private Integer leftCount;

    @ApiModelProperty("每人限领 精确匹配")
    private Integer userLimit;

    @ApiModelProperty("优惠券金额 精确匹配")
    private BigDecimal couponAmount;

    @ApiModelProperty("最低消费金额 精确匹配")
    private BigDecimal minAmount;

    @ApiModelProperty("要兑换的积分 精确匹配")
    private BigDecimal useIntegral;

    @ApiModelProperty("1免费兑换  2积分兑换 精确匹配")
    private Integer couponType;

    @ApiModelProperty("活动开始时间 精确匹配")
    private LocalDateTime beginTime;

    @ApiModelProperty("活动结束时间 精确匹配")
    private LocalDateTime endTime;

}
