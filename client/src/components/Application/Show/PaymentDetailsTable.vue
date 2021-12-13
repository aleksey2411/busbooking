<template>
    <div class="col-6">
      <div
        class="card-header border-0"
        :class="type === 'dark' ? 'bg-transparent' : ''"
      >
        <div class="row align-items-center">
          <div class="col">
            <h3 class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
              Application Payment Details
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
              <th>Initial Payment</th>
              <th v-if="application.paymentDetails.initPaymentPayedAt !== null">Initial Payment Payed At</th>
              <th>Payment Per Month</th>
              <th>Month Count</th>
            </template>

            <template v-slot:default="row">
              <td class="budget">
                {{ row.item.paymentDetails.initPayment }} $
              </td>
              <td v-if="row.item.paymentDetails.initPaymentPayedAt !== null" class="budget">
                {{  row.item.paymentDetails.initPaymentPayedAt }}
              </td>
              <td class="budget">
                {{ row.item.paymentDetails.paymentPerMonth }} $
              </td>
              <td class="budget">
                {{ row.item.paymentDetails.monthCount }}
              </td>
            </template>
          </base-table>
        </div>
    </div>
    </div>
</template>

<script>

export default {
  name: "application-payment-details-table",
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
