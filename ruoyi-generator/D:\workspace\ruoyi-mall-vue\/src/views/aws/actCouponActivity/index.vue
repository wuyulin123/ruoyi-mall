<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px" size="medium" class="ry_form">
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
      <el-form-item label="发行总数" prop="totalCount">
        <el-input
          v-model="queryParams.totalCount"
          placeholder="请输入发行总数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余总数" prop="leftCount">
        <el-input
          v-model="queryParams.leftCount"
          placeholder="请输入剩余总数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每人限领" prop="userLimit">
        <el-input
          v-model="queryParams.userLimit"
          placeholder="请输入每人限领"
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
      <template v-if="showMoreCondition">
      <el-form-item label="最低消费金额" prop="minAmount">
        <el-input
          v-model="queryParams.minAmount"
          placeholder="请输入最低消费金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="活动开始时间" prop="beginTime">
        <el-date-picker
            clearable
            size="small"
            v-model="queryParams.beginTime"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss"
            placeholder="选择活动开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间" prop="endTime">
        <el-date-picker
            clearable
            size="small"
            v-model="queryParams.endTime"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss"
            placeholder="选择活动结束时间">
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
          v-hasPermi="['aws:actCouponActivity:add']"
        >新增</el-button>
      </el-col>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="ActCouponActivityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动名称" align="center" prop="title" />
      <el-table-column label="使用范围  1全场通用 2指定商品可用 3指定商品不可用" align="center" prop="useScope" />
      <el-table-column label="商品id集合，逗号分隔" align="center" prop="productIds" />
      <el-table-column label="发行总数" align="center" prop="totalCount" />
      <el-table-column label="剩余总数" align="center" prop="leftCount" />
      <el-table-column label="每人限领" align="center" prop="userLimit" />
      <el-table-column label="优惠券金额" align="center" prop="couponAmount" />
      <el-table-column label="最低消费金额" align="center" prop="minAmount" />
      <el-table-column label="要兑换的积分" align="center" prop="useIntegral" />
      <el-table-column label="1免费兑换  2积分兑换" align="center" prop="couponType" />
      <el-table-column label="活动开始时间" align="center" prop="beginTime" width="180" >
        <template slot-scope="scope">
            <span>{{ parseTime(scope.row.beginTime, '')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="endTime" width="180" >
        <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endTime, '')}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aws:actCouponActivity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aws:actCouponActivity:remove']"
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

    <!-- 添加或修改优惠券活动表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="108px" inline class="dialog-form-two">
        <el-form-item label="活动名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="使用范围  1全场通用 2指定商品可用 3指定商品不可用" prop="useScope">
          <el-input v-model="form.useScope" placeholder="请输入使用范围  1全场通用 2指定商品可用 3指定商品不可用" />
        </el-form-item>
        <el-form-item label="商品id集合，逗号分隔" prop="productIds">
          <el-input v-model="form.productIds" placeholder="请输入商品id集合，逗号分隔" />
        </el-form-item>
        <el-form-item label="发行总数" prop="totalCount">
          <el-input v-model="form.totalCount" placeholder="请输入发行总数" />
        </el-form-item>
        <el-form-item label="剩余总数" prop="leftCount">
          <el-input v-model="form.leftCount" placeholder="请输入剩余总数" />
        </el-form-item>
        <el-form-item label="每人限领" prop="userLimit">
          <el-input v-model="form.userLimit" placeholder="请输入每人限领" />
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
        <el-form-item label="活动开始时间" prop="beginTime">
          <el-date-picker clearable size="small"
                        v-model="form.beginTime"
                        type="datetime"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        placeholder="选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="endTime">
          <el-date-picker clearable size="small"
                        v-model="form.endTime"
                        type="datetime"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        placeholder="选择活动结束时间">
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
import { listActCouponActivity, getActCouponActivity, delActCouponActivity, addActCouponActivity, updateActCouponActivity, exportActCouponActivity } from "@/api/aws/actCouponActivity";

export default {
  name: "ActCouponActivity",
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
      // 优惠券活动表表格数据
      ActCouponActivityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        useScope: null,
        productIds: null,
        totalCount: null,
        leftCount: null,
        userLimit: null,
        couponAmount: null,
        minAmount: null,
        useIntegral: null,
        couponType: null,
        beginTime: null,
        endTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        useScope: [
          { required: true, message: "使用范围  1全场通用 2指定商品可用 3指定商品不可用不能为空", trigger: "blur" }
        ],
        totalCount: [
          { required: true, message: "发行总数不能为空", trigger: "blur" }
        ],
        leftCount: [
          { required: true, message: "剩余总数不能为空", trigger: "blur" }
        ],
        userLimit: [
          { required: true, message: "每人限领不能为空", trigger: "blur" }
        ],
        couponAmount: [
          { required: true, message: "优惠券金额不能为空", trigger: "blur" }
        ],
        couponType: [
          { required: true, message: "1免费兑换  2积分兑换不能为空", trigger: "change" }
        ],
        beginTime: [
          { required: true, message: "活动开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "活动结束时间不能为空", trigger: "blur" }
        ],
      },
      showMoreCondition: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询优惠券活动表列表 */
    getList() {
      this.loading = true;
      const {pageNum, pageSize} = this.queryParams;
      const query = {...this.queryParams, pageNum: undefined, pageSize: undefined};
      const pageReq = {page: pageNum - 1, size: pageSize};
      listActCouponActivity(query, pageReq).then(response => {
        const { content, totalElements } = response
        this.ActCouponActivityList = content;
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
        title: null,
        useScope: null,
        productIds: null,
        totalCount: null,
        leftCount: null,
        userLimit: null,
        couponAmount: null,
        minAmount: null,
        useIntegral: null,
        couponType: null,
        beginTime: null,
        endTime: null,
        createTime: null,
        updateTime: null
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
      this.title = "添加优惠券活动表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getActCouponActivity(id).then(response => {
        this.form = response;
        this.open = true;
        this.title = "修改优惠券活动表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateActCouponActivity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActCouponActivity(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除优惠券活动表编号为"' + ids + '"的数据项？').then(function() {
        return delActCouponActivity(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有优惠券活动表数据项？').then(() => {
        this.exportLoading = true;
        return exportActCouponActivity(queryParams);
      }).then(response => {
        this.$download.download(response);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
