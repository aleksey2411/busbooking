<template>

  <div class="auth-wrapper">
    <div class="auth-inner">
      <div class="booking--wrapper">
        <div class="info--title">
          <div class="title--line"></div>
          <h2 v-if="isTicketsLoaded">Мои поездки</h2>
          <h2 v-else>Поездки отсутствуют</h2>
          <div class="title--line"></div>
        </div>
        <div class="container">
        </div>
      </div>
    </div>
  </div>
  <div v-if="isTicketsLoaded" class="col">
    <div class="card shadow">
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Дата</th>
            <th>Время</th>
            <th>Маршрут</th>
            <th>Водитель</th>
            <th>Рейтинг водителя</th>
            <th>Автомобиль</th>
            <th>Детали</th>
            <th>Статус</th>
            <th></th>
          </tr>
          </thead>
          <tbody class="list">
          <tr
            v-for="ticket of data.tickets"
            :key="ticket.id"
            :value="ticket.id"
          >
            <th scope="row">
              <div class="media align-items-center">
                <div class="media-body">
                    <span class="name mb-0 text-sm">{{
                        ticket.date
                      }}</span>
                </div>
              </div>
            </th>
            <td class="budget">
              <div class="">{{ ticket.departureDateTime }}</div>
              <div class="">{{ ticket.tripDTO.arrivalDateTime }}</div>
            </td>
            <td>
              <div class="">
                {{ ticket.tripDTO.routeDTO.departureCityDTO.name }}
              </div>
              <div class="">
                {{ ticket.tripDTO.routeDTO.arrivalCityDTO.name }}
              </div>
            </td>
            <td>
              <div>
                {{ ticket.tripDTO.driverDTO.firstName }}
                {{ ticket.tripDTO.driverDTO.lastName }}
              </div>
            </td>
            <td>
              <div class="d-flex align-items-center">
                <span class="completion mr-2">70%</span>
                <div>
                  <div class="progress-wrapper pt-0">
                    <!---->
                    <div class="progress" style="height: 3px">
                      <div
                        class="progress-bar bg-warning"
                        role="progressbar"
                        aria-valuenow="60"
                        aria-valuemin="0"
                        aria-valuemax="100"
                        style="width: 60%"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </td>
            <td class="text-left">
              {{ ticket.tripDTO.busDTO.mark }}
              {{ ticket.tripDTO.busDTO.model }},
              {{ ticket.tripDTO.busDTO.color }},
              {{ ticket.tripDTO.busDTO.number }}
            </td>
            <td class="text-left">
              <div>{{ ticket.numberOfSeats }}</div>
              <div>{{ ticket.tripDTO.routeDTO.cost }} BYN</div>
            </td>
            <td>
                <span class="badge badge-warning badge-dot mr-4">
                  <i v-if="ticket.ticketStatus=='Забронирован'" class="bg-info"></i>
                  <i v-else-if="ticket.ticketStatus=='Подтвержден'" class="bg-success"></i>
                  <i v-else class="bg-danger"></i>
                  <span class="status">
                  {{ ticket.ticketStatus }}</span></span
                >
            </td>
            <td>
              <button @click="rejectTicket(ticket.id)" class="btn btn-danger">Отменить</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="container mt-10">
    <div class="container">
      <img
        class="img-center"
        src="/img/not_found.jpeg"
        width="600"
        alt=""
      />
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import { api } from "../../scripts/axios/axios";

export default {
  name: "user-tickets",
  setup() {
    const isTicketsLoaded = ref(false);
    const data = reactive({
      tickets: []
    });

    const rejectTicket = (ticketId) => {
      api.application.rejectTicket(ticketId).then((response) => {
        console.log(response);
        getTickets();
      });
    };

    const getTickets = () => {
      api.application
        .ticketsByUser()
        .then((response) => {
          data.tickets = response.data;
          data.tickets.forEach((ticket) => {
            let departure = new Date(ticket.departureDateTime);
            let arrival = new Date(ticket.tripDTO.arrivalDateTime);
            ticket.date = departure.toLocaleDateString();
            ticket.departureDateTime = departure.toLocaleTimeString();
            ticket.tripDTO.arrivalDateTime = arrival.toLocaleTimeString();
            if (ticket.numberOfSeats == 1) {
              ticket.numberOfSeats = ticket.numberOfSeats + " место";
            } else if (ticket.numberOfSeats == 5) {
              ticket.numberOfSeats = ticket.numberOfSeats + " мест";
            } else {
              ticket.numberOfSeats = ticket.numberOfSeats + " места";
            }
            if (ticket.ticketStatus == "BOOKED") {
              ticket.ticketStatus = "Забронирован";
            }
            if (ticket.ticketStatus == "CONFIRMED") {
              ticket.ticketStatus = "Подтвержден";
            }
            if (ticket.ticketStatus == "REJECTED") {
              ticket.ticketStatus = "Отменен";
            }
          });
          if (data.tickets.length !== 0) {
            isTicketsLoaded.value = true;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };

    onMounted(() => {
      getTickets()
    });

    return {
      isTicketsLoaded,
      data,
      getTickets,
      rejectTicket
    };
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

th {
  font-size: 1rem !important;
}


.partners--wrap {
  background: #ffffff;
  box-shadow: 0 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
  margin: 10px 0 20px -15px;
  padding: 60px 16px 60px;
  margin-bottom: 43px;
}

.ml-not-found {
  margin-left: 550px;
}

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
  margin-bottom: 142px;
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
</style>
