<template>
  <div class="auth-wrapper">
    <div class="auth-inner">
      <div class="booking--wrapper">
        <div class="info--title">
          <div class="title--line"></div>
          <h2>Бронирование</h2>
          <div class="title--line"></div>
        </div>
        <div class="booking--find--wrapper">
          <div class="booking--form--container">
            <form action="" method="GET" class="booking--find">
              <div class="form--mod">
                <p>откуда</p>
                <select  v-model="data.departureCity" class="custom-select black-color" name="" id="">
                  <option
                    v-for="city of data.cities"
                    :key="city.id"
                    v-bind:value="{id: city.id}"
                  >
                    {{ city.name }}
                  </option>
                </select>
              </div>
              <div class="form--mod">
                <p>куда</p>
                <select v-model="data.arrivalCity" class="black-color custom-select">
                  <option
                    v-for="city of data.cities"
                    :key="city.id"
                    v-bind:value="{id: city.id}"
                  >
                    {{ city.name }}
                  </option>
                </select>
              </div>
              <div class="form--mod">
                <p>дата</p>
                <flat-picker
                  @on-open="focus"
                  @on-close="blur"
                  :config="config"
                  class="form-control black-color datepicker"
                  v-model="data.date"
                >
                </flat-picker>
              </div>
              <div class="form--mod">
                <p>пассажиры</p>
                <select class="black-color custom-select">
                  <option>1 пассажир</option>
                  <option>2 пассажира</option>
                  <option>3 пассажира</option>
                  <option>4 пассажира</option>
                  <option>5 пассажиров</option>
                </select>
              </div>
              <div>
                <button @click="findTrips" type="button" class="form-submit">
                  Найти
                </button>
              </div>
            </form>
          </div>
          <div class="help-inner"></div>
        </div>
        <div class="output--header">
          <trips-for-auth v-if="isAuth" :trips="data.trips" />
          <trips v-else :trips="data.trips" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
import { api } from "../../scripts/axios/axios";
import { onMounted, reactive, ref } from "vue";
import moment from "moment";
import Trips from "./Trips";
import TripsForAuth from "./TripsForAuth";

export default {
  name: "booking",
  components: {
    flatPicker,
    Trips,
    TripsForAuth
  },
  data() {
    return {
      dates: {
        simple: new Date()
      },
      route: null,
      isTripsLoaded: false,
      trips: [],
      config: {
        minDate: new Date(),
        altInput: true,
        format: "yyyy-MM-dd"
      }
    };
  },
  methods: {

  },
  setup() {
    const isAuth = ref(false);
    const isCitiesLoaded = ref(false);
    const isTripsLoaded = ref(false);
    const data = reactive({
      cities: [],
      trips: [],
      route: '',
      departureCity: '',
      arrivalCity: '',
      date: new Date()
    });

    onMounted(() => {
      if (localStorage.token !== undefined) {
        isAuth.value = true;
      } else {
        isAuth.value = false;
      }
      api.application
        .cities()
        .then((response) => {
          data.cities = response.data;
          if (data.cities.length !== 0) {
            isCitiesLoaded.value = true;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    });


    const findTrips = () => {

      let date = moment(data.date).format("yyyy-MM-DD");
      api.application
        .routesByCities(data.departureCity.id, data.arrivalCity.id)
        .then((response) => {
          data.route = response.data;
          api.application
            .tripsByRoute(data.route.id, date)
            .then((newResponse) => {
              data.trips = newResponse.data;
              console.log(data.trips)
              data.trips.forEach((trip) => {
                let depTime = new Date(trip.departureDateTime);
                let arrTime = new Date(trip.arrivalDateTime);
                trip.departureDateTime = depTime.getHours() + ":" + depTime.getMinutes();
                trip.arrivalDateTime = arrTime.getHours() + ":" + arrTime.getMinutes();
              });
              if (newResponse.data.length !== 0) {
                isTripsLoaded.value = true;
              }
            });
        })
        .catch((error) => {
          isTripsLoaded.value = false;
          console.log(error);
        });
    }

    return {
      findTrips,
      isAuth,
      data,
      isCitiesLoaded
    };
  }

};
</script>

<style scoped>
.container,
.container-sm,
.container-md,
.container-lg,
.container-xl {
  max-width: 1303px;
  margin-top: 100px;
}

.partners--wrap {
  background: #ffffff;
  box-shadow: 0 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
  margin: 10px 0 20px -15px;
  padding: 60px 16px 60px;
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

.black-color {
  color: black;
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
</style>
