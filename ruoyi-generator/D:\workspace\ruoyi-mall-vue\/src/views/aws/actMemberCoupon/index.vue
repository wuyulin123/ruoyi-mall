<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px" size="medium" class="ry_form">
      <el-form-item label="活动id" prop="couponActivityId">
        <el-input
          v-model="queryParams.couponActivityId"
          placeholder="请输入活动id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="memberId">
        <el-input
          v-model="queryParams.memberId"
          placeholder="请输入用户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用范围  1全场通用 2指定商品可用 3指定商品不可用" prop="useScope">
        <el-input
          v-model="queryParams.useScope"
          placeholder="请输入使用范围  1全场通用 2指定商品可用 3指定商品不可用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品id集合，逗号分隔" prop="productIds">
        <el-input
          v-model="queryParams.productIds"
          placeholder="请输入商品id集合，逗号分隔"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券金额" prop="couponAmount">
        <el-input
          v-model="queryParams.couponAmount"
          placeholder="请输入优惠券金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最低消费金额" prop="minAmount">
        <el-input
          v-model="queryParams.minAmount"
          placeholder="请输入最低消费金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <template v-if="showMoreCondition">
      <el-form-item label="要兑换的积分" prop="useIntegral">
        <el-input
          v-model="queryParams.useIntegral"
          placeholder="请输入要兑换的积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="1免费兑换  2积分兑换" prop="couponType">
        <el-select v-model="queryParams.couponType" placeholder="请选择1免费兑换  2积分兑换" clearable size="small">
              <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="券开始时间" prop="beginTime">
        <el-date-picker
            clearable
            size="small"
            v-model="queryParams.beginTime"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss"
            placeholder="选择券开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="券结束时间" prop="endTime">
        <el-date-picker
            clearable
            size="small"
            v-model="queryParams.endTime"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss"
            placeholder="选择券结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="0未使用  1已使用" prop="useStatus">
        <el-select v-model="queryParams.useStatus" placeholder="请选择0未使用  1已使用" clearable size="small">
              <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="订单id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用时间" prop="useTime">
        <el-date-picker
            clearable
            size="small"
            v-model="queryParams.useTime"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss"
            placeholder="选择使用时间">
        </el-date-picker>
      </el-form-item>
    </template>
      <el-form-item class="flex_one tr">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button :icon="showMoreCondition ? 'el-icon-arrow-up' : 'el-icon-arrow-down'" size="mini" @click="showMoreCondition = !showMoreCondition">{{showMoreCondition ? '收起条件' : '展开条件'}}</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['aws:actMemberCoupon:add']"
        >新增</el-button>
      </el-col>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="ActMemberCouponList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动id" align="center" prop="couponActivityId" />
      <el-table-column label="用户id" align="center" prop="memberId" />
      <el-table-column label="活动名称" align="center" prop="title" />
      <el-table-column label="使用范围  1全场通用 2指定商品可用 3指定商品不可用" align="center" prop="useScope" />
      <el-table-column label="商品id集合，逗号分隔" align="center" prop="productIds" />
      <el-table-column label="优惠券金额" align="center" prop="couponAmount" />
      <el-table-column label="最低消费金额" align="center" prop="minAmount" />
      <el-table-column label="要兑换的积分" align="center" prop="useIntegral" />
      <el-table-column label="1免费兑换  2积分兑换" align="center" prop="couponType" />
      <el-table-column label="券开始时间" align="center" prop="beginTime" width="180" >
        <template slot-scope="scope">
            <span>{{ parseTime(scope.row.beginTime, '')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="券结束时间" align="center" prop="endTime" width="180" >
        <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endTime, '')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="0未使用  1已使用" align="center" prop="useStatus" />
      <el-table-column label="订单id" align="center" prop="orderId" />
      <el-table-column label="使用时间" align="center" prop="useTime" width="180" >
        <template slot-scope="scope">
            <span>{{ parseTime(scope.row.useTime, '')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aws:actMemberCoupon:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aws:actMemberCoupon:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户领券记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="108px" inline class="dialog-form-two">
        <el-form-item label="活动id" prop="couponActivityId">
          <el-input v-model="form.couponActivityId" placeholder="请输入活动id" />
        </el-form-item>
        <el-form-item label="用户id" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="活动名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="使用范围  1全场通用 2指定商品可用 3指定商品不可用" prop="useScope">
          <el-input v-model="form.useScope" placeholder="请输入使用范围  1全场通用 2指定商品可用 3指定商品不可用" />
        </el-form-item>
        <el-form-item label="商品id集合，逗号分隔" prop="productIds">
          <el-input v-model="form.productIds" placeholder="请输入商品id集合，逗号分隔" />
        </el-form-item>
        <el-form-item label="优惠券金额" prop="couponAmount">
          <el-input v-model="form.couponAmount" placeholder="请输入优惠券金额" />
        </el-form-item>
        <el-form-item label="最低消费金额" prop="minAmount">
          <el-input v-model="form.minAmount" placeholder="请输入最低消费金额" />
        </el-form-item>
        <el-form-item label="要兑换的积分" prop="useIntegral">
          <el-input v-model="form.useIntegral" placeholder="请输入要兑换的积分" />
        </el-form-item>
        <el-form-item label="1免费兑换  2积分兑换" prop="couponType">
          <el-select v-model="form.couponType" placeholder="请选择1免费兑换  2积分兑换">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="券开始时间" prop="beginTime">
          <el-date-picker clearable size="small"
                        v-model="form.beginTime"
                        type="datetime"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        placeholder="选择券开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="券结束时间" prop="endTime">
          <el-date-picker clearable size="small"
                        v-model="form.endTime"
                        type="datetime"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        placeholder="选择券结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="0未使用  1已使用">
          <el-radio-group v-model="form.useStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单id" />
        </el-form-item>
        <el-form-item label="使用时间" prop="useTime">
          <el-date-picker clearable size="small"
                        v-model="form.useTime"
                        type="datetime"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        placeholder="选择使用时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listActMemberCoupon, getActMemberCoupon, delActMemberCoupon, addActMemberCoupon, updateActMemberCoupon, exportActMemberCoupon } from "@/api/aws/actMemberCoupon";

export default {
  name: "ActMemberCoupon",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户领券记录表格数据
      ActMemberCouponList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        couponActivityId: null,
        memberId: null,
        title: null,
        useScope: null,
        productIds: null,
        couponAmount: null,
        minAmount: null,
        useIntegral: null,
        couponType: null,
        beginTime: null,
        endTime: null,
        useStatus: null,
        orderId: null,
        useTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        couponActivityId: [
          { required: true, message: "活动id不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        useScope: [
          { required: true, message: "使用范围  1全场通用 2指定商品可用 3指定商品不可用不能为空", trigger: "blur" }
        ],
        couponAmount: [
          { required: true, message: "优惠券金额不能为空", trigger: "blur" }
        ],
        couponType: [
          { required: true, message: "1免费兑换  2积分兑换不能为空", trigger: "change" }
        ],
        beginTime: [
          { required: true, message: "券开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "券结束时间不能为空", trigger: "blur" }
        ],
        useStatus: [
          { required: true, message: "0未使用  1已使用不能为空", trigger: "blur" }
        ],
      },
      showMoreCondition: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户领券记录列表 */
    getList() {
      this.loading = true;
      const {pageNum, pageSize} = this.queryParams;
      const query = {...this.queryParams, pageNum: undefined, pageSize: undefined};
      const pageReq = {page: pageNum - 1, size: pageSize};
      listActMemberCoupon(query, pageReq).then(response => {
        const { content, totalElements } = response
        this.ActMemberCouponList = content;
        this.total = totalElements;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        couponActivityId: null,
        memberId: null,
        title: null,
        useScope: null,
        productIds: null,
        couponAmount: null,
        minAmount: null,
        useIntegral: null,
        couponType: null,
        beginTime: null,
        endTime: null,
        useStatus: 0,
        orderId: null,
        useTime: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户领券记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getActMemberCoupon(id).then(response => {
        this.form = response;
        this.open = true;
        this.title = "修改用户领券记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateActMemberCoupon(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActMemberCoupon(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户领券记录编号为"' + ids + '"的数据项？').then(function() {
        return delActMemberCoupon(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有用户领券记录数据项？').then(() => {
        this.exportLoading = true;
        return exportActMemberCoupon(queryParams);
      }).then(response => {
        this.$download.download(response);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
