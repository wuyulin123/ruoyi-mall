import request from '@/utils/request'

// 查询用户领券记录列表
export function listActMemberCoupon(query, pageReq) {
  return request({
    url: '/aws/actMemberCoupon/list',
    method: 'post',
    data: query,
    params: pageReq
  })
}

// 查询用户领券记录详细
export function getActMemberCoupon(id) {
  return request({
    url: '/aws/actMemberCoupon/' + id,
    method: 'get'
  })
}

// 新增用户领券记录
export function addActMemberCoupon(data) {
  return request({
    url: '/aws/actMemberCoupon',
    method: 'post',
    data: data
  })
}

// 修改用户领券记录
export function updateActMemberCoupon(data) {
  return request({
    url: '/aws/actMemberCoupon',
    method: 'put',
    data: data
  })
}

// 删除用户领券记录
export function delActMemberCoupon(id) {
  return request({
    url: '/aws/actMemberCoupon/' + id,
    method: 'delete'
  })
}

// 导出用户领券记录
export function exportActMemberCoupon(query) {
  return request({
    url: '/aws/actMemberCoupon/export',
    method: 'get',
    params: query
  })
}
