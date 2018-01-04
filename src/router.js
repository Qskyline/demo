import login from './views/login'
import register from './views/register'
import home from './views/home'
import test from './views/test'

export default [
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/home',
    name: 'home',
    component: home
  },
  {
    path: '/test',
    name: 'test',
    component: test
  }
]
