import { ElMessage } from 'element-plus'
import router from '../router'
import axios from "axios";

const request = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 30000  // 后台接口超时时间设置
})


request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    const user = localStorage.getItem("system-user");
    if(user){
        config.headers['token'] = JSON.parse(user).token;
    }
    console.log("Local Storage system-user:", JSON.parse(user));
    return config
}, error => {
    return Promise.reject(error)
});


request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 当权限验证不通过的时候给出提示
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push("/login")
        }
        return res;
    },
        error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)


export default request
