<template>
  <div class="container mb-4 border-left border-bottom border-right border-top border-light">
    <div class="card-body p-lg-5"><h4 class="mb-1">Хотите работать с нами?</h4>
      <p class="mt-0">Хотите стать партнером компании Стрела? Заполните форму, и мы свяжемся с Вами.</p>
      <div class="form-group mt-3 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-user-run"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Имя партнера" class="form-control font-weight-bold"><!----><!---->
      </div>
      <div class="form-group mt-3 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-mobile-button"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Телефон" class="form-control font-weight-bold"><!----><!---->
      </div>
      <div class="form-group mt-3 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-square-pin"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Маршрут" class="form-control font-weight-bold"><!----><!---->
      </div>
      <div class="form-group mt-3 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-bus-front-12"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Сколько у вас авто?" class="form-control font-weight-bold"><!----><!---->
      </div>
      <button @click="$router.push('help')" type="button" class="btn btn-block btn-default btn-lg" round=""><!----><!----><!---->
        Отправить
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "for-carriers"
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