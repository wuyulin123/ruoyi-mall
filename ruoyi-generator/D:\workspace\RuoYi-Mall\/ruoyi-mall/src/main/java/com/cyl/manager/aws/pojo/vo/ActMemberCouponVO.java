package com.cyl.manager.aws.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 用户领券记录 数据视图对象
 * 
 * @author zcc
 */
@Data
public class ActMemberCouponVO  {
   /** ID */
    private Long id;
   /** 活动id */
    @Excel(name = "活动id")
    private Long couponActivityId;
   /** 用户id */
    @Excel(name = "用户id")
    private Long memberId;
   /** 活动名称 */
    @Excel(name = "活动名称")
    private String title;
   /** 使用范围  1全场通用 2指定商品可用 3指定商品不可用 */
    @Excel(name = "使用范围  1全场通用 2指定商品可用 3指定商品不可用")
    private Integer useScope;
   /** 商品id集合，逗号分隔 */
    @Excel(name = "商品id集合，逗号分隔")
    private String productIds;
   /** 优惠券金额 */
    @Excel(name = "优惠券金额")
    private BigDecimal couponAmount;
   /** 最低消费金额 */
    @Excel(name = "最低消费金额")
    private BigDecimal minAmount;
   /** 要兑换的积分 */
    @Excel(name = "要兑换的积分")
    private BigDecimal useIntegral;
   /** 1免费兑换  2积分兑换 */
    @Excel(name = "1免费兑换  2积分兑换")
    private Integer couponType;
   /** 券开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "券开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
   /** 券结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "券结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
   /** 0未使用  1已使用 */
    @Excel(name = "0未使用  1已使用")
    private Integer useStatus;
   /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;
   /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime useTime;
   /** 创建时间 */
    private LocalDateTime createTime;
}
