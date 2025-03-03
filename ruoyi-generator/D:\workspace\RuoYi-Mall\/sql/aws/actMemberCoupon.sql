-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录', '3', '1', 'actMemberCoupon', 'aws/actMemberCoupon/index', 1, 0, 'C', '0', '0', 'aws:actMemberCoupon:list', '#', 1, sysdate(), '', null, '用户领券记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aws:actMemberCoupon:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aws:actMemberCoupon:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aws:actMemberCoupon:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aws:actMemberCoupon:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户领券记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aws:actMemberCoupon:export',       '#', 1, sysdate(), '', null, '');
