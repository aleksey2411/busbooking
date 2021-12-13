<template>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Город</h3></div>
          <div class="col text-right">
            <button type="button" class="btn btn-primary  btn-sm"><!----><!----><!---->See all</button>
          </div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Остановка</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Маршрут</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Водитель</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Авто</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Поездка</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>


  <div class="col">
    <div class="card shadow">
      <div class="card-header border-0">
        <div class="row align-items-center">
          <div class="col"><h3 class="mb-0">Билеты</h3></div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table tablesorter table align-items-center table-flush">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Название</th>
          </tr>
          </thead>
          <tbody class="list">
          <tr>
            <td>kek</td>
            <td>lol</td>
          </tr>
          <tr>
            <td>
            <input
              aria-describedby="addon-right addon-left"
              placeholder=""
              class="mt-60 font-comic"
            />
            </td>
            <td>
              <input
                aria-describedby="addon-right addon-left"
                placeholder=""
                class="mt-60 font-comic"
              />
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import { api } from "../../scripts/axios/axios";

export default {
  name: "admin-tables",

  setup() {
    const isTicketsLoaded = ref(false);
    const data = reactive({
      tickets: [],
      departureDate: "",
      arrivalDate: ""
    });

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
    const confirmTicket = (ticketId) => {
      api.application.confirmTicket(ticketId).then((response) => {
        console.log(response);
        getTickets();
      });
    };

    const rejectTicket = (ticketId) => {
      api.application.rejectTicket(ticketId).then((response) => {
        console.log(response);
        getTickets();
      });
    };

    onMounted(() => {
      getTickets();
    });

    return {
      isTicketsLoaded,
      data,
      confirmTicket,
      rejectTicket,
      getTickets
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

.mb-10 {
  margin-bottom: 10px;
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
