<template>
  <div>
    <div style="padding-left: 60px; font-size: 20px; font-weight: bold; margin-bottom: 20px">系统公告</div>
    
  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  noticeList: []
})

// 请求后台的公告数据
request.get('/notice/selectAll').then(res => {
  data.noticeList = res.data
})
</script>