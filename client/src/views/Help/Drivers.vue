<template>
  <div class="container mb-4 border-left border-bottom border-right border-top border-light">
    <div class="card-body p-lg-5"><h4 class="mb-1">Хотите работать с нами?</h4>
      <p class="mt-0">Если вы ищете работу водителем автобуса, заполните форму ниже, и мы отправим вашу заявку
        перевозчикам — партнерам Стрела в вашем регионе</p>
      <div class="form-group mt-5 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-pin-3"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Ваш город" class="form-control font-weight-bold">
        <!----><!---->
      </div>
      <div class="col-lg-3 col-md-6">
        <div class="mb-3"><small class="text-uppercase font-weight-bold">Категории прав</small></div>
        <div class="custom-control custom-checkbox mb-3"><input id="LsRVJoP" type="checkbox"
                                                                class="custom-control-input"><label for="LsRVJoP"
                                                                                                    class="custom-control-label">
          B
        </label></div>
        <div class="custom-control custom-checkbox mb-3"><input id="wmEcbjv" type="checkbox"
                                                                class="custom-control-input"><label for="wmEcbjv"
                                                                                                    class="custom-control-label">
          D
        </label></div>
      </div>
      <div class="form-group mt-3 mb-3 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-mobile-button"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Телефон" class="form-control font-weight-bold">
        <!----><!---->
      </div>
      <div class="form-group mt-2 input-group input-group-alternative"><!---->
        <div class="input-group-prepend"><span class="input-group-text"><i class="ni ni-user-run"></i></span></div>
        <input aria-describedby="addon-right addon-left" placeholder="Имя" class="form-control font-weight-bold"><!---->
        <!---->
      </div>
      <div class="form-group mb-4"><!----><!----><textarea name="name" rows="4" cols="80"
                                                           placeholder="Комментарий..."
                                                           class="form-control font-weight-bold form-control-alternative"></textarea>
        <!----><!----></div>
      <button @click="$router.push('help')" type="button" class="btn btn-block btn-default btn-lg" round=""><!----><!----><!---->
        Отправить
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "for-drivers"
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