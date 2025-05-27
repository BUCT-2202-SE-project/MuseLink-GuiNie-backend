<template>
  <div>
    <!-- 查询区域 -->
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.orderNo" style="width: 300px; margin-right: 10px" placeholder="请输入订单编号查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <!-- 订单列表 -->
    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="订单编号" prop="orderId"></el-table-column>
        <el-table-column label="农产品名称" prop="goodName"></el-table-column>
        <el-table-column label="经手人" prop="returnName"></el-table-column>
        <el-table-column label="总价" prop="totalPrice"></el-table-column>
        <el-table-column label="退单时间" prop="returnDate"></el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>
  </div>
</template>
<script setup>
import request from "@/utils/request";
import { reactive } from "vue";
import { ElMessageBox, ElMessage } from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  orderNo: null
});

// 分页查询
const load = () => {
  request.get('/returns/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      orderId: data.orderNo,
      userId: data.user.id
    }
  }).then(res => {
    console.log("查询到的表格数据:", res.data?.list || '无数据');
    data.tableData = res.data?.list;
    data.total = res.data?.total;
  });
};

// 重置
const reset = () => {
  data.orderNo = null;
  load();
};

load();
</script>