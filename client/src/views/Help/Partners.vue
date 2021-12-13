<template>
  <div class="container">
  <div class="partners--wrap">
    <div class="grid-container">
      <div class="name">Имя</div>
      <div class="adress"><p>юридический адрес</p></div>
      <div class="unp"><p>УНП</p></div>
      <div class="time"><p>часы работы</p></div>
    </div>
    <div class="grid-container">
      <div class="name"><p>ЧП ПреВит</p></div>
      <div class="adress"><p>г. Лида ул. Калинина 45</p></div>
      <div class="unp"><p>590721596</p></div>
      <div class="time"><p>8.00-17.00</p></div>
    </div>
    <div class="grid-container">
      <div class="name"><p>ООО Надёжный Вектор</p></div>
      <div class="adress"><p>220014 г Минск, ул.Серафимовича 13</p></div>
      <div class="unp"><p>193270332</p></div>
      <div class="time"><p>9.00-16.00</p></div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "partners"
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
.partners--wrap {
  margin: 0 auto;
  background: #FFFFFF;
  box-shadow: 0px 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
  margin-top: 10px;
  margin-left: -15px;
  margin-right: -15px;
  margin-bottom: 20px;
  padding: 60px 16px 60px;
}

.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  grid-gap: 0px 0px;
  gap: 0px 0px;
  grid-template-areas:
        "name adress unp time"
        ". . . ."
        ". . . .";
  border-bottom: 3px solid #F5F5F5;
}
</style>