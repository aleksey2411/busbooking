<template>
    <div class="col-6">
      <div
        class="card-header border-0"
        :class="type === 'dark' ? 'bg-transparent' : ''"
      >
        <div class="row align-items-center">
          <div class="col">
            <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
              Application Delivery Details
            </h3>
          </div>
        </div>
      </div>
      <div v-if="application" class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
        <div class="table-responsive">
          <base-table v-if="[application]"
                      class="table align-items-center table-flush"
                      :class="type === 'dark' ? 'table-dark' : ''"
                      :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
                      tbody-classes="list"
                      :data="[application]"
          >
            <template v-slot:columns>
              <th>Delivery Scheduled At</th>
              <th v-if="application.deliveryDates.bookedAt !== null">Delivery Booked At</th>
              <th v-if="application.deliveryDates.contractLiveBy !== null">Contract Live By</th>
            </template>

            <template v-slot:default="row">
              <td class="budget">
                {{ row.item.deliveryDates.scheduledAt }}
              </td>
              <td v-if="row.item.deliveryDates.bookedAt !== null" class="budget">
                {{  row.item.deliveryDates.bookedAt }}
              </td>
              <td v-if="row.item.deliveryDates.contractLiveBy !== null" class="budget">
                {{ row.item.deliveryDates.contractLiveBy }}
              </td>
            </template>
          </base-table>
        </div>
    </div>
    </div>

</template>

<script>

export default {
  name: "application-delivery-details-table",
  props: {
    application: Object,
  },
  data() {
    return {
      tableDate: [],
      type: 'light',
    };
  },
};
</script>
