<template>
  <div>
    <!-- 查询 -->
    <el-form inline>
      <el-input v-model="search.userId" placeholder="用户ID" />
      <el-input v-model="search.artifactId" placeholder="文物ID" />
      <el-button type="primary" @click="load">查询</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="tableData" style="margin-top: 10px">
      <el-table-column label="内容" prop="content" />
      <el-table-column label="时间" prop="publishTime" />
      <el-table-column label="用户ID" prop="userId" />
      <el-table-column label="文物ID" prop="artifactId" />
      <el-table-column label="审核状态" prop="reviewStatus" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          <el-button type="danger" @click="del(scope.row.commentId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加按钮 -->
    <el-button type="success" @click="openAddForm" style="margin-top: 10px">新增评论</el-button>

    <!-- 表单对话框：新增和编辑复用 -->
    <el-dialog v-model="formVisible" :title="form.commentId ? '编辑评论' : '新增评论'">
      <el-form :model="form">
        <el-input v-model="form.content" placeholder="内容" />
        <el-input v-model="form.userId" placeholder="用户ID" />
        <el-input v-model="form.artifactId" placeholder="文物ID" />
        <el-select v-model="form.reviewStatus" placeholder="审核状态">
          <el-option label="通过" value="通过" />
          <el-option label="未通过" value="未通过" />
        </el-select>
      </el-form>
      <template #footer>
        <el-button @click="formVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import request from '@/utils/request'

const tableData = ref([])
const formVisible = ref(false)
const form = reactive({ content: '', userId: '', artifactId: '', reviewStatus: '未通过', commentId: null })
const search = reactive({ userId: '', artifactId: '' })

const load = () => {
  request.get('/comment/select', { params: search }).then(res => {
    tableData.value = res.data
  })
}

const save = () => {
  if (form.commentId) {
    // 编辑时调用 update 接口
    request.put('/comment/update', form).then(() => {
      formVisible.value = false
      load()
    })
  } else {
    // 新增时调用 add 接口
    request.post('/comment/add', form).then(() => {
      formVisible.value = false
      load()
    })
  }
}

const del = (id) => {
  request.delete(`/comment/delete/${id}`).then(() => load())
}

const edit = (row) => {
  Object.assign(form, row) // 将选中行数据复制到表单中
  formVisible.value = true
}

const openAddForm = () => {
  // 重置表单（用于新增）
  Object.assign(form, { content: '', userId: '', artifactId: '', reviewStatus: '未通过', commentId: null })
  formVisible.value = true
}

load()
</script>
