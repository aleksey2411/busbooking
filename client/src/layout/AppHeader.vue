<template>
  <nav class="navbar mt-0 navbar-expand-lg navbar-light bg-white">
    <div class="container">
      <button
        @click="$router.push('/')"
        class="btn btn-outline-secondary btn-lg">
        <img src="/img/logo.png">
      </button>
      <button
        @click="$router.push('/booking')"
        class="btn btn-outline-secondary btn-lg">
        <i class="ni ni-delivery-fast" />
        <span class="nav-link-inner--text text-dark text-lg">Найти</span>
      </button>
      <button
        @click="$router.push('/help')"
        class="btn btn-lg btn-outline-secondary">
        <i class="ni ni-support-16" />
        <span class="nav-link-inner--text text-dark text-lg">Помощь</span>
      </button>
      <ul class="navbar-nav align-items-lg-center ml-lg-auto">

        <li class="nav-item d-none d-lg-block ml-lg-4">
          <button
            v-show="isAuth"
            @click="routeHome"
            class="btn btn-icon btn-outline-secondary">
            <i class="ni ni-bus-front-12" />
            <span class="nav-link-inner--text text-dark text-lg">Поездки</span>
          </button>
          <button
            v-if="isAuth"
            @click="logout"
            class="btn btn-icon btn-outline-secondary">
            <i class="ni ni-circle-08" />
            <span class="nav-link-inner--text text-dark text-lg">Выйти</span>
          </button>
          <button
            v-else
            @click="login"
            class="btn btn-icon btn-outline-secondary">
            <i class="ni ni-circle-08" />
            <span class="nav-link-inner--text text-dark text-lg">Войти</span>
          </button>

        </li>
      </ul>
    </div>

  </nav>
</template>
<script>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "app-header",
  setup() {
    const router = useRouter();
    const isAuth = ref(false);

    const logout = () => {
        isAuth.value = false;
        router.push('logout');
    }

    const routeHome = () => {
        if(localStorage.role === 'ROLE_USER') {
          router.push('/user-tickets')
        }  else if (localStorage.role === 'ROLE_ADMIN') {
          router.push('/admin-tickets')
        }
    }


    const login = () => {
      router.push('login');
    }

    onMounted(() => {
      if (localStorage.token !== undefined) {
        isAuth.value= true;
      } else {
        isAuth.value = false
      }
    });
    return {
      login,
      logout,
      isAuth,
      routeHome
    };
  }
};
</script>
<style>
</style>