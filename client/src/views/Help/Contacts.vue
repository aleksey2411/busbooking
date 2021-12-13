<template>
  <div class="container  mb-4 border-left border-bottom border-right border-top border-light">
    <div class="card-body p-lg-5"><h4 class="mb-1">Связаться с нами</h4>
      <div class="form-group mt-5 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-user-run"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Ваше имя" class="form-control"><!----><!---->
      </div>
      <div class="form-group input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-email-83"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Ваша почта" class="form-control"><!----><!---->
      </div>
      <div class="form-group mb-4"><!----><!----><textarea name="name" rows="4" cols="80"
                                                           placeholder="Ваше сообщение..."
                                                           class="form-control form-control-alternative"></textarea>
        <!----><!----></div>
      <button @click="$router.push('help')" type="button" class="btn btn-block btn-default btn-lg" round=""><!----><!----><!---->
        Отправить
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "contacts"
};
</script>

<style scoped>

.contacts--wrapper .form--wrapper {
  padding: 65px;
}

.contacts--wrapper .form--wrapper h3 {
  font-size: 24px;
}

@media (min-width: 1200px) {

  .container, .container-sm, .container-md, .container-lg, .container-xl {
    max-width: 1303px;
    margin-top: 100px;
  }
}
</style>