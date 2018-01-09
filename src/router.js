import login from './views/login'
import register from './views/register'
import home from './views/home'
import test from './views/test'
import demo from './modules/demo'

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
    component: home,
    children:[
      {
        path: '',
        name: 'demo',
        component: demo
      }
    ]
  },
  {
    path: '/test',
    name: 'test',
    component: test
  }
]
