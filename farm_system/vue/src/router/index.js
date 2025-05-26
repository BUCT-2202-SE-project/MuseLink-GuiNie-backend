import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: () => import('@/views/Manager.vue'),
      redirect: '/home',
      children: [
        { path: 'person', component: () => import('@/views/manager/Person.vue')},
        { path: 'password', component: () => import('@/views/manager/Password.vue')},
        { path: 'home', component: () => import('@/views/manager/Home.vue')},
        { path: 'admin', component: () => import('@/views/manager/Admin.vue')},
        { path: 'notice', component: () => import('@/views/manager/Notice.vue')},
        { path: 'artifact', component: () => import('@/views/manager/Artifact.vue')},
        { path: 'qa', component: () => import('@/views/manager/Qa.vue')},
        { path: 'goodsStock', component: () => import('@/views/manager/GoodsStock.vue')},
        { path: 'backup', component: () => import('@/views/manager/backup.vue')},
        { path: 'user', component: () => import('@/views/manager/user.vue')},
        { path: 'orders', component: () => import('@/views/manager/Orders.vue')},
        { path: 'log', component: () => import('@/views/manager/log.vue')},
        { path: 'relog', component: () => import('@/views/manager/relog.vue')},
        { path: 'recomment', component: () => import('@/views/manager/recomment.vue')},
        { path: 'returns', component: () => import('@/views/manager/Ordersreturn.vue')},
        { path: 'reviewer', component: () => import('@/views/manager/Reviewer.vue')},
      ]
    },
    { path: '/login', component: () => import('@/views/Login.vue')},
    { path: '/register', component: () => import('@/views/Register.vue')},
  ]
})

export default router
