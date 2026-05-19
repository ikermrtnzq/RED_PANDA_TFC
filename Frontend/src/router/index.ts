import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',      // Cuando el usuario entra a la raíz
      redirect: '/inicio' // Lo mandamos automáticamente a /tienda
    },
    {
      path: '/inicio',
      name: 'inicio',
      component: () => import('@/views/InicioView.vue')
    },
    {
      path: '/tienda',
      name: 'tienda',
      component: () => import('@/views/TiendaView.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('@/views/AdminView.vue')
    },
    {
      path: '/carrito',
      name: 'carrito',
      component: () => import('@/views/CarritoView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/LoginView.vue')
    }
    ,
    {
      path: '/perfil',
      name: 'perfil',
      component: () => import('@/views/UserView.vue')
    }
  ],
})

export default router
