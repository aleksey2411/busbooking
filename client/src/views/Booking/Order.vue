<template>
  <div class="auth-wrapper">
    <div class="auth-inner">
      <div class="booking--wrapper">
        <div class="info--title">
          <div class="title--line"></div>
          <h2>Заказ</h2>
          <div class="title--line"></div>
        </div>
      </div>
    </div>
  </div>
  <section class="section pb-0 section-components">
    <div class="container mb-5">
      <div class="row">
        <div class="col-lg-4 col-sm-6">
          <div class="form-group">
            <div class="container mb-4">
              <div class="card-body p-lg-5">
                <div class="font-comic mg-arrival">  {{data.departureCity}}  </div>

                <div class="row-grid">
                  <img class="img-fluid" src="/img/route.png" />
                </div>

                <div class="font-comic mg-departure"> {{ data.arrivalCity }} </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-8">
          <div class="form-group">
            <div class="container mb-4">
              <div class="container mb-1 border">
                <div class="col-form-label font-select">
                  Выберите остановку:
                </div>
                <select @mouseup="setDepartureTime" v-model="data.busStop" class="custom-select font-select">
                  <option

                    v-for="busstop of data.busStops"
                    :key="busstop.id"
                    v-bind:value="{busStop: busstop}"
                  >
                    {{ busstop.busStopDTO.name }}
                  </option>
                </select>

                <div class="col-form-label font-select">
                  Выберите количество мест:
                </div>
                <select v-model="data.countOfSeats" class="custom-select font-select">
                  <option
                    v-for="count of data.count"
                    :key="count"
                    v-bind:value="{selected: count}"
                  >
                    {{ count }}
                  </option>
                </select>


                <input
                  disabled
                  aria-describedby="addon-right addon-left"
                  placeholder=""
                  class="mt-60 font-comic"
                  v-model="departureTime"
                />
                <div class="label-fade-enter">Время отправления</div>
                <button class="btn btn-danger mt-button" @click="buyTicket">
                  Забронировать
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { api } from "../../scripts/axios/axios";
import { onMounted, reactive, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

export default {
  name: "order",
  setup() {
    const route = useRoute();
    const router = useRouter();
    const data = reactive({
      trip: '',
      arrivalCity: '',
      departureCity:'',
      count: [1, 2, 3, 4, 5],
      countOfSeats: '',
      busStops: [],
      busStop: ''
    });


    const departureTime = ref('');

    const setDepartureTime = () => {
      let datetime = new Date(data.trip.departureDateTime);
      let hour = datetime.getHours();
      let minute = datetime.getMinutes();
      minute = minute+data.busStop.busStop.timeToBusStopInMinutes;
      departureTime.value = hour + ':' + minute;
    }


    onMounted(() => {
      api.application
        .trip(route.params.id)
        .then((response) => {
          data.trip = response.data;
          console.log(data.trip)
          data.departureCity = data.trip.routeDTO.departureCityDTO.name;
          data.arrivalCity = data.trip.routeDTO.arrivalCityDTO.name;
          api.application.busStop(data.trip.routeDTO.id).then((newResponse) => {
            console.log(newResponse.data)
            data.busStops = newResponse.data;
          });
        })
        .catch((error) => {
          console.log(error);
        });
    });

    const buyTicket = () => {

      let busStopId = data.busStop.busStop.id;
      api.application
        .buyTicket(data.trip.id, busStopId, data.countOfSeats.selected)
        .then(() => {
          router.push('home')
        });
    };

    return {
      data,
      buyTicket,
      departureTime,
      setDepartureTime
    };
  }
};
</script>

<style scoped>
.contacts--wrapper .form--wrapper {
  padding: 25px;
}

.contacts--wrapper .form--wrapper h3 {
  font-size: 24px;
}

.font-comic {
  font-style: oblique;
  font-size: 30px !important;
  color: #030100;
}

.border {
  border: 1px #333333;
}

.mg-arrival {
  margin-bottom: 20px;
}

.mg-departure {
  margin-top: 20px;
  margin-left: 100px;
}

.font-select {
  font-size: 20px;
  color: black;
}

@media (min-width: 1200px) {
  .container,
  .container-sm,
  .container-md,
  .container-lg,
  .container-xl {
    max-width: 1303px;
    margin-top: 50px;
  }
}

.container,
.container-sm,
.container-md,
.container-lg,
.container-xl {
  max-width: 1303px;
  margin-top: 50px;
}

.partners--wrap {
  background: #ffffff;
  box-shadow: 0 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
  margin: 10px 0 20px -15px;
  padding: 60px 16px 60px;
}

.mt-button {
  margin-top: 22px;
  margin-left: 170px;
  margin-bottom: 10px;
}

.mt-60 {
  display: block;
  width: 100%;
  height: calc(1.5em + 1.25rem + 2px);
  padding: 0.625rem 0.75rem;
  font-size: 0.875rem;
  font-weight: 400;
  line-height: 1.5;
  color: #8898aa;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #cad1d7;
  border-radius: 0.375rem;
  box-shadow: none;
  transition: all 0.2s cubic-bezier(0.68, -0.55, 0.265, 1.55);

  margin-top: 50px !important;
}

.info--title {
  font-size: 36px;
  text-transform: uppercase;
  text-align: center;
  display: flex;
  justify-content: center;
  padding-top: 150px;
}

.mt-10 {
  margin-bottom: 311px;
}

h2 {
  display: block;
  font-size: 1em !important;
  font-weight: bold;
  margin-top: -20px;
  margin-bottom: 40px !important;
  font: inherit !important;
}

.form-submit {
  background: #ee1c1c;
  box-shadow: 0 4px 4px rgba(225, 130, 130, 0.24);
  border-radius: 0 7px 7px 0;
  width: 215.8px;
  color: white;
  height: 94px;
  outline: none;
  border: none;
}

.title--line {
  position: relative;
  width: 135px;
  height: 3px;
  background: #ee1c1c;
  margin: 5px 35px;
  vertical-align: center;
}

.booking--wrapper {
  max-width: 1300px;
  margin: auto;
}

.booking--find--wrapper {
  margin-top: 80px;
}

.booking--find--wrapper .booking--form--container form {
  display: flex;
}

.form--mod {
  width: 265px;
  height: 71px;
  background: #ffffff;
  box-shadow: 0 4px 5px rgb(0 0 0 / 25%);
}

.form--mod:first-child {
  border-radius: 5px 1px 1px 5px;
}

.booking--form--container {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}

p {
  margin-top: 10px;
  margin-bottom: 1rem;
  margin-left: 87px;
}

.row {
  margin-left: 70px;
}

.contacts--wrapper .form--wrapper h3 {
  font-size: 24px;
}

@media (min-width: 1200px) {
}

.partners--wrap {
  background: #ffffff;
  box-shadow: 0 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
  margin: 10px 0 20px -15px;
  padding: 60px 16px 60px;
}
</style>
