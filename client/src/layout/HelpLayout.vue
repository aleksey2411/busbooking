<template>
  <app-header/>
  <help/>
  <router-view/>
  <app-footer/>
</template>

<script>
import AppFooter from "./AppFooter";
import AppHeader from "./AppHeader";
import Help from "../views/Help/Help";

export default {
  components: {
    AppHeader, AppFooter, Help
  }
}
</script>

<style scoped>

</style>
