import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from './store';
import { api } from './scripts/axios/axios';
import { apiKey } from './scripts/symbols';
import ArgonDashboard from "./plugins/argon-dashboard";
import "element-plus/lib/theme-chalk/index.css";

const app = createApp(App);

app.use(router);
app.use(store);
app.use(ArgonDashboard);

app.provide(apiKey, api);

app.mount("#app");
