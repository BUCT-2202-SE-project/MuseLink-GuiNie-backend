<template>
  <div>

    <!-- 查询区域 -->
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.reviewerId" style="width: 300px; margin-right: 10px" placeholder="请输入审核员ID查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <!-- 表格及操作按钮 -->
    <div class="card" style="margin-bottom: 5px">
     
      <el-table :data="data.tableData" stripe>
        <el-table-column label="审核ID" prop="reviewId"></el-table-column>
        <el-table-column label="内容类型" prop="contentType"></el-table-column>
        <el-table-column label="关联评论ID" prop="commentId"></el-table-column>
        <el-table-column label="关联动态ID" prop="postId"></el-table-column>
        <el-table-column label="审核结果" prop="result"></el-table-column>
        <el-table-column label="审核时间" prop="reviewTime"></el-table-column>
        <el-table-column label="审核员ID" prop="reviewerId"></el-table-column>
        <el-table-column label="操作" header-align="center" width="160">
          <template #default="scope">
           
            <el-button type="danger" @click="handleDelete(scope.row.reviewId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页 -->
    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="prev, pager, next" 
                     v-model:page-size="data.pageSize" 
                     v-model:current-page="data.pageNum" 
                     :total="data.total"/>
    </div>

    <!-- 对话框 -->
    <el-dialog title="审核记录" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">
        <el-form-item label="内容类型">
          <el-select v-model="data.form.contentType" placeholder="请选择内容类型" style="width: 100%">
            <el-option value="评论" label="评论"></el-option>
            <el-option value="动态" label="动态"></el-option>
            <el-option value="媒体" label="媒体"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评论ID">
          <el-input v-model="data.form.commentId" placeholder="仅在内容为评论时填写"></el-input>
        </el-form-item>
        <el-form-item label="动态ID">
          <el-input v-model="data.form.postId" placeholder="仅在内容为动态时填写"></el-input>
        </el-form-item>
        <el-form-item label="审核结果">
          <el-select v-model="data.form.result" placeholder="请选择审核结果" style="width: 100%">
            <el-option value="通过" label="通过"></el-option>
            <el-option value="未通过" label="未通过"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核时间">
          <el-date-picker style="width: 100%" type="datetime" v-model="data.form.reviewTime" 
                          placeholder="请选择审核时间" format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"></el-date-picker>
        </el-form-item>
        <el-form-item label="审核员ID">
          <el-input v-model="data.form.reviewerId" placeholder="请输入审核员ID"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>
<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  reviewerId: null
})

const load = () => {
  request.get('/review/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      reviewerId: data.reviewerId
    }
  }).then(res => {
    data.tableData = res.data?.list || [];
    data.total = res.data?.total || 0;
  })
}

const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

const save = () => {
  if (data.form.reviewId) {
    update()
  } else {
    add()
  }
}

const add = () => {
  request.post('/review/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const update = () => {
  request.put('/review/update', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const handleDelete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(() => {
    request.delete(`/review/delete/${id}`).then(res => {
      if (res.code === '200') {
        load()
        ElMessage.success('操作成功')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(() => {})
}

const reset = () => {
  data.reviewerId = null
  load()
}

load()
</script>
