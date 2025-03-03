import request from '@/utils/request'

// 查询优惠券活动表列表
export function listActCouponActivity(query, pageReq) {
  return request({
    url: '/aws/actCouponActivity/list',
    method: 'post',
    data: query,
    params: pageReq
  })
}

// 查询优惠券活动表详细
export function getActCouponActivity(id) {
  return request({
    url: '/aws/actCouponActivity/' + id,
    method: 'get'
  })
}

// 新增优惠券活动表
export function addActCouponActivity(data) {
  return request({
    url: '/aws/actCouponActivity',
    method: 'post',
    data: data
  })
}

// 修改优惠券活动表
export function updateActCouponActivity(data) {
  return request({
    url: '/aws/actCouponActivity',
    method: 'put',
    data: data
  })
}

// 删除优惠券活动表
export function delActCouponActivity(id) {
  return request({
    url: '/aws/actCouponActivity/' + id,
    method: 'delete'
  })
}

// 导出优惠券活动表
export function exportActCouponActivity(query) {
  return request({
    url: '/aws/actCouponActivity/export',
    method: 'get',
    params: query
  })
}
