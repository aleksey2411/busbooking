<template>
  <div v-if="trips.length !== 0 " class="container">
    <div
      class="partners--wrap"
      v-for="trip of trips"
      :key="trip.id"
      :value="trip.id"
    >
      <div class="row">
        <div class="col-md-1">
          <div class="row-cols-md-1">
            {{ trip.departureDateTime }}0
          </div>
          <div class="row-cols-md-1">{{ trip.arrivalDateTime }}0</div>
        </div>
        <div class="col-md-2">
          <div class="row-cols-md-1">
            {{ trip.routeDTO.departureCityDTO.name }}
          </div>
          <div class="row-cols-md-1">
            {{ trip.routeDTO.arrivalCityDTO.name }}
          </div>
        </div>
        <div class="col-md-3">
          <div class="row-cols-md-1">Водитель</div>
          <div class="row-cols-md-1">{{ trip.driverDTO.firstName }} {{ trip.driverDTO.lastName }}</div>

        </div>
        <div class="col-md-3">
          <div class="row-cols-md-1">Автомобиль</div>
          <div class="row-cols-md-1">{{ trip.busDTO.mark }} {{ trip.busDTO.model }}, {{ trip.busDTO.number }}</div>
        </div>
        <div class="col-md-2">
          <div class="row-cols-md-2">Свободных мест: {{ trip.numberOfFreeSeats}}</div>
          <div class="row-cols-md-1">{{trip.routeDTO.cost}} BYN</div>
        </div>
      </div>
    </div>
  </div>

  <div class="container mt-10" v-else></div>
</template>

<script>

export default {
  name: "trips-for-auth",
  data() {
    return {};
  },
  props: {
    trips: Array
  },
  setup() {

  }
};
</script>

<style scoped>
.row {
  margin-left: 10px;
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
  margin-bottom: 43px;
}
</style>
