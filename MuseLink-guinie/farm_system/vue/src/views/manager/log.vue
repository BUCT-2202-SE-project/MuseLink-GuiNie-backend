<template>
  <div>

    <!-- 查询区域 -->
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.operatorId" style="width: 300px; margin-right: 10px" placeholder="请输入操作用户ID查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <!-- 表格 -->
    <div class="card">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="操作类型" prop="operationType"></el-table-column>
        <el-table-column label="操作详情" prop="operationDetail"></el-table-column>
        <el-table-column label="操作时间" prop="operationTime"></el-table-column>
        <el-table-column label="操作用户ID" prop="operationId"></el-table-column>
    
        <el-table-column label="操作" header-align="center" width="100">
          <template #default="scope">
            <el-button type="danger" @click="handleDelete(scope.row.logId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <div class="card" v-if="data.total">
      <el-pagination
        @current-change="load"
        background
        layout="prev, pager, next"
        v-model:page-size="data.pageSize"
        v-model:current-page="data.pageNum"
        :total="data.total"
      />
    </div>




  </div>
</template>

<script setup>
import {reactive} from 'vue'
import request from '@/utils/request'
import {ElMessageBox, ElMessage} from 'element-plus'

const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  operatorId: null,
  formVisible: false,
  form: {}
})

const load = () => {
  request.get('/log/selectPage', {
    params: {
      operatorId: data.operatorId, // 如果你用 form 作为查询条件对象
      pageNum: data.pageNum,
      pageSize: data.pageSize
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total || 0
        console.log('Data after loading logs:', data);
  })
}
const load1 = () => {
  request.get('/log/selectPage', {
    params: {
      operatorId: -1, // 如果你用 form 作为查询条件对象
      pageNum: data.pageNum,
      pageSize: data.pageSize
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total || 0
        console.log('Data after loading logs:', data);
  })
}
// 新增日志
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// 保存日志
const save = () => {
  request.post('/log/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('添加成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 删除日志
const handleDelete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，确定删除？', '删除确认', { type: 'warning' }).then(() => {
    request.delete(`/log/delete/${id}`).then(res => {
      if (res.code === '200') {
        load1()
        ElMessage.success('删除成功')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(() => {})
}

// 重置查询条件
const reset = () => {
  data.operatorId = null
  load1()
}

load1()
</script>
