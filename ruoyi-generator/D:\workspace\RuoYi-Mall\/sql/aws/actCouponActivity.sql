-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表', '3', '1', 'actCouponActivity', 'aws/actCouponActivity/index', 1, 0, 'C', '0', '0', 'aws:actCouponActivity:list', '#', 1, sysdate(), '', null, '优惠券活动表菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aws:actCouponActivity:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aws:actCouponActivity:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aws:actCouponActivity:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aws:actCouponActivity:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('优惠券活动表导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aws:actCouponActivity:export',       '#', 1, sysdate(), '', null, '');
