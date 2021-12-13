<script>
import { onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default {
  name: 'logout',
  setup() {
    const store = useStore();
    const router = useRouter();
    onMounted(() => {
      store.dispatch('auth/logout');
      router.push({ name: 'home' });
    });
  },
};
</script>