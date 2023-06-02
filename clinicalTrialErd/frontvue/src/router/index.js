import Vue from 'vue';
import Router from 'vue-router';

import Dashboard from '../pages/Dashboard.vue';
import Social from '../pages/Social.vue';
import Media from '../pages/Media.vue';
import Snackbar from '../pages/Snackbar.vue';

import Mailbox from '../pages/Mailbox.vue';
import Calendar from '../pages/Calendar.vue';
import Login from '../pages/core/Login.vue';
import Error from '../pages/core/Error.vue';
import Register from '../pages/core/Register.vue';
import Visit from '../pages/Visit.vue';
import Investigation from '../pages/Investigation.vue';
import Administration from '../pages/Administration.vue';

Vue.use(Router);
export default new Router({
  //mode: 'history',
  routes: [
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      meta: { breadcrumb: [{ name: 'dashboard' } ] }
    },
    {
      path: '/mailbox',
      name: 'Mailbox',
      component: Mailbox,
      meta: { breadcrumb: [{ name: 'dashboard', href: 'Dashboard' }, { name: 'mailbox' }]}
    },
    {
      path: '/snackbar',
      name: 'Snackbar',
      component: Snackbar,
      meta: { breadcrumb: [{ name: 'dashboard', href: 'Dashboard' }, { name: 'snackbar' }] }
    },
    {
      path: '/calendar',
      name: 'Calendar',
      component: Calendar,
      meta: { breadcrumb: [ { name: 'dashboard', href: 'Dashboard' }, { name: 'calendar' }] }
    },
    {
      path: '/social',
      name: 'Social',
      component: Social,
      meta: { breadcrumb: [ { name: 'dashboard', href: 'Dashboard' }, { name: 'social' } ] }
    },
    {
      path: '/media',
      name: 'Media',
      component: Media,
      meta: { breadcrumb: [ { name: 'dashboard', href: 'Dashboard' }, { name: 'media' } ]  }
    },
    {
      path: '/visit',
      name: 'Visit',
      component: Visit,
      meta: { breadcrumb: [ { name: 'v1', href: 'V1' }, { name: 'visit' } ] }
    },
    {
      path: '/investigation',
      name: 'Investigation',
      component: Investigation,
      meta: { breadcrumb: [ { name: 'v1', href: 'V1' }, { name: 'investigation' } ] }
    },
    {
      path: '/administration',
      name: 'Administration',
      component: Administration,
      meta: { breadcrumb: [ { name: 'v1', href: 'V1' }, { name: 'administration' } ] }
    },
    {
      path: '/',
      name: 'Login',
      component: Login,
      meta: { allowAnonymous: true }
    },
    {
      path: '/error',
      name: 'Error',
      component: Error,
      meta: { allowAnonymous: true }
    },
    {
      path: "*",
      name: "Error",
      component: Error,
      meta: { allowAnonymous: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
      meta: { allowAnonymous: true }
    },
  ]
});
