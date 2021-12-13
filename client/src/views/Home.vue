<template>
  <div class="container">
    <img class="img-center" src="/img/bus-main.png" width="1400" alt="">
  </div>
  <div class="trust--wrap"><h2 class="section--description">нам доверяют</h2>
    <div class="trust--info--wrap">
      <ul>
        <li class="info"><h3 class="count">1</h3>
          <p class="sub--description">неделя опыта пассажирских перевозок</p></li>
        <li class="info"><h3 class="count">4</h3>
          <p class="sub--description">буса в автопарке</p></li>
        <li class="info"><h3 class="count">10</h3>
          <p class="sub--description">рейсов за текущий месяц</p></li>
        <li class="info"><h3 class="count">80</h3>
          <p class="sub--description">пассажиров за текущий месяц</p></li>
      </ul>
    </div>
  </div>


  <div class="advantages--wrap"><h3>почему мы</h3>
    <div class="advantages--content--wrap">
      <div class="advantages--column">
        <div class="advantages--item"><p>лучший оффициальный перевозчик в Лиде</p></div>
        <div class="advantages--item"><p>водители со стажем более 5 лет</p></div>
        <div class="advantages--item"><p>круглосуточная техподдержка</p></div>
      </div>
      <div class="advantages--column">
        <div class="advantages--item"><p>наши пассажиры застрахованы</p></div>
        <div class="advantages--item"><p>отправление каждые 30 минут</p></div>
        <div class="advantages--item"><p>комфортные автомобили с кондиционером</p></div>
      </div>
    </div>
  </div>
  <br><br><br><br>
</template>


<script>


export default {
  name: "home",
  data() {
    return {
      year: new Date().getFullYear(),
      showMenu: false
    };
  },
};
</script>

<style scoped>
ody {
  margin: 0;
  font-family: Open Sans, sans-serif;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #525f7f;
  text-align: left;
  background-color: #FFFFFF !important;
}

.trust--wrap {
  color: #fff;
  height: 333px;
  background-color: #ee1c1c;
  margin-top: 150px;
}

.section--description {
  font-family: Bahnschrift;
  font-style: normal;
  font-weight: normal;
  font-size: 36px;
  color: white;
  line-height: 43px;
  text-align: center;
  padding-top: 46px;
  text-transform: uppercase;
}

.trust--info--wrap {
  margin: 0 auto;
  padding-top: 20px;
}

.trust--info--wrap ul {
  display: flex;
  justify-content: center;
  text-align: center;
  list-style: none;
}

.trust--info--wrap ul .info {
  max-width: 255px;
  border-right: solid #fff 2px;
  padding: 0px 35px;
  list-style: none;
}

.trust--info--wrap ul .info .count {
  font-size: 72px;
  color: white;
}

.sub--description {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 17px !important;
  font: inherit;
  vertical-align: baseline;
}

.advantages--wrap {
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
  margin: 120px auto;
  background: url("/img/small-arrow.svg") no-repeat center;
  background-size: 240px;
  background-position-y: 37px;
  max-width: 930px;
}

.advantages--wrap .advantages--content--wrap {
  display: flex;
  justify-content: space-between;
  width: 100%;
  text-align: center;
}

.advantages--wrap .advantages--column {
  display: flex;
  flex-direction: column;
}

.advantages--wrap .advantages--column .advantages--item {
  margin-top: 50px;
  width: 386px;
  height: 77px;
  background: #FFFFFF;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.advantages--wrap .advantages--column .advantages--item p {
  padding: 3vh 27px;
}

.advantages--wrap .advantages--column .advantages--item:first-child {
  background: #EE1C1C;
  box-shadow: 0px 4px 10px rgb(0 0 0 / 10%), inset 0px 2px 11px -2px rgb(0 0 0 / 25%);
  color: #fff;
}

.advantages--wrap .advantages--column .advantages--item p {
  padding: 3vh 27px;
}

@media (max-width: 768px) {
  .advantages--content--wrap {
    width: 390px;
    flex-wrap: wrap;
    flex-direction: column;
    margin: 0 auto;
  }
}

@media (max-width: 768px) {

  .advantages--content--wrap advantages--column {
    margin: 0 auto;
  }
}

.advantages--wrap h3 {
  text-transform: uppercase;
  font-size: 36px;
  line-height: 43px;
  text-align: center;

}
</style>
