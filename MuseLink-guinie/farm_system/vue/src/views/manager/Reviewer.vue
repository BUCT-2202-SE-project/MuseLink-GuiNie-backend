<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.name" style="width: 300px; margin-right: 10px" placeholder="请输入名称查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="data.tableData" stripe>
        <el-table-column label="用户ID" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="注册时间" prop="registerTime"></el-table-column>
        <el-table-column label="用户角色" prop="role"></el-table-column>
        <el-table-column label="权限状态" prop="permissionStatus"></el-table-column>
        <el-table-column label="操作" header-align="center" width="160">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.userId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog title="信息" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="data.form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="data.form.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="data.form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="权限状态">
          <el-select v-model="data.form.permissionStatus" placeholder="选择权限状态" style="width: 100%">
            <el-option value="正常" label="正常"></el-option>
            <el-option value="受限" label="受限"></el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="权限状态" prop="permissionStatus">
                  <el-input v-model="data.form.permissionStatus" autocomplete="off" />
                </el-form-item>-->
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

// const currentUserStr = localStorage.getItem("system-user");
// let currentUserName = ""
// if (currentUserStr) {
//   const currentUser = JSON.parse(currentUserStr);
//   currentUserName = currentUser.username;
//   console.log("currentUserName:", currentUserName);
// } else {
//   console.log("没有找到用户信息");
// }

// 文件上传的接口地址
const uploadUrl = import.meta.env.VITE_BASE_URL + '/files/upload'

const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form:{
  },
  tableData: [],
  options: [],
  name: null
})

// 分页查询
const load = () => {
  request.get('/reviewer/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    console.log("Line105 data.name: ",data.name)
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}

// 新增
const handleAdd = () => {
  data.form = {
  }
  data.formVisible = true
}

// 编辑
const handleEdit = (row) => {
  console.log("row: ",row)
  data.form = {
    ...JSON.parse(JSON.stringify(row)),
  }
  data.formVisible = true
}

// 新增保存
const add = () => {
  //console.log(data.form)
  request.post('/reviewer/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 编辑保存
const update = () => {
  request.put('/reviewer/update', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 弹窗保存
const save = () => {
  // data.form有id就是更新，没有就是新增
  data.form.userId ? update() : add()
}

// 删除
const handleDelete = (userId) => {
  console.log("userId: ",userId)
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
    request.delete('/reviewer/delete/' + userId, {
      params: { operator: data.form.operator }
    }).then(res => {
      if (res.code === '200') {
        load()
        ElMessage.success('操作成功')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

// 重置
const reset = () => {
  data.name = null
  load()
}

// 处理文件上传的钩子
const handleImgSuccess = (res) => {
  data.form.imageUrl = res.data  // res.data就是文件上传返回的文件路径，获取到路径后赋值表单的属性
}

load()
</script>