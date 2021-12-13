<template>
  <div v-if="application" class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <base-progress
        v-if="application.applicationStage && !isInactive"
        :type="progressBarColor"
        :height="8"
        :value="application.applicationStage.level * 10"
        :label="application.applicationStage.label"
      />
      <div class="row align-items-center">
        <div class="col">
          <h3 v-if="isInactive">
            Individual Application. {{application.applicationId}}
            <badge type="danger">{{ application.inactiveReason.status }}</badge> <br/>
            <span class="text-red">Reason: {{ application.inactiveReason.message }}</span>
          </h3>
          <h3 v-else class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            Individual Application. {{application.applicationId}}
          </h3>
        </div>
        <div class="col text-right">
          <base-button class="link-custom" v-if="isDeclineAvailable" @click="declineModal.modal = true" type="danger" size="sm">Decline</base-button>
          <base-button class="link-custom" v-if="isCancelAvailable" @click="cancelModal.modal = true" type="danger" size="sm">Cancel</base-button>

          <base-button class="link-custom" v-if="isDraft" @click="$emit('onProvideDriverLicense')" type="success" size="sm">Provide Driver License</base-button>
          <base-button class="link-custom" v-if="isProvideDriverLicense" @click="$emit('onSubmitQuote')" type="success" size="sm">Submit Quote</base-button>
          <base-button class="link-custom" v-if="isQuoteAvailable" @click="commissionNumberModal.modal = true" type="success" size="sm">Provide Commission Number</base-button>
          <base-button class="link-custom" v-if="isCommissionNumberProvided" @click="$emit('onApprove')" type="success" size="sm">Approve</base-button>
          <base-button class="link-custom" v-if="isApproved" @click="$emit('onPrepareContract')" type="success" size="sm">Prepare Contract</base-button>
          <base-button class="link-custom" v-if="isContractPreparation" @click="$emit('onSigningContract')" type="success" size="sm">Sign In contract</base-button>
          <base-button class="link-custom" v-if="isCustomerSignIn" @click="$emit('onProvideInitialPayment')" type="success" size="sm">Provide Initial Payment</base-button>
          <base-button class="link-custom" v-if="isInitialPaymentProvided" @click="$emit('onScheduleDelivery')" type="success" size="sm">Schedule Delivery</base-button>
          <base-button class="link-custom" v-if="isDeliveryBookingInProgress" @click="$emit('onConfirmDelivery')" type="success" size="sm">Confirm Delivery</base-button>
          <base-button class="link-custom" v-if="isDeliveryBooked" @click="$emit('onDeliveryPassed')" type="success" size="sm">Delivery Passed</base-button>

          <base-button class="link-custom" v-if="type === 'dark'" @click="toggleToLight" type="primary" size="sm">Toggle to Light</base-button>
          <base-button class="link-custom" v-else @click="toggleToDark" type="primary" size="sm">Toggle to Dark</base-button>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table v-if="[application]"
                  class="table align-items-center table-flush"
                  :class="type === 'dark' ? 'table-dark' : ''"
                  :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
                  tbody-classes="list"
                  :data="[application]"
      >
        <template v-slot:columns>
          <th>Customer Name</th>
          <th>Broker Name</th>
          <th v-if="application.vehicleVid">Vehicle VID</th>
          <th v-if="application.commissionNumber">Commission Number</th>
          <th v-if="application.registrationNumber">Registration Number</th>
          <th>Full Price</th>
          <th>Is Delivery Included</th>
          <th v-if="isInactive">Canceled By</th>
          <th>Created At</th>
          <th v-if="isInactive">Inactivated At</th>
          <th></th>
        </template>

        <template v-slot:default="row">
          <td>
            <badge class="badge-dot mr-4" type="success">
              <i :class="`bg-info`"></i>
              <span class="status">{{ row.item.customerName }}</span>
            </badge>
          </td>
          <td>
            <badge class="badge-dot mr-4" type="success">
              <i :class="`bg-warning`"></i>
              <span class="status">{{ row.item.brokerName }}</span>
            </badge>
          </td>
          <td
            v-if="row.item.vehicleVid"
            class="budget nav-link"
            style="cursor: pointer"
            @click="$router.push({ name: 'vehicle', params: { id: row.item.vehicleVid.vehicleId } }).catch(() => {})"
          >
            <strong>{{ row.item.vehicleVid.vehicleVid }}</strong>
          </td>
          <td v-if="row.item.commissionNumber" class="budget">
            {{ row.item.commissionNumber }}
          </td>
          <td v-if="row.item.registrationNumber" class="budget">
            {{ row.item.registrationNumber }}
          </td>
          <td class="budget">
            $ {{ row.item.fullPrice }} USD
          </td>
          <td style="text-align: center">
            <badge  style="margin-left: -30px" class="large" type="info">{{ row.item.isDeliveryIncluded ? 'Yes' : 'No' }}</badge>
          </td>
          <td v-if="isInactive">
            <badge class="badge-dot mr-4" type="warning">
              <i :class="`bg-warning`"></i>
              <span class="status">{{ row.item.canceledBy.name }}</span>
            </badge>
          </td>
          <td class="budget">
            {{ row.item.createdAt }}
          </td>
          <td v-if="isInactive" class="budget">
            {{ row.item.inactiveReason.inactivatedAt }}
          </td>
        </template>
      </base-table>
    </div>

    <!-- decline modal -->
    <modal v-model:show="declineModal.modal"
           modal-classes="modal-danger modal-dialog-centered">
      <div class="py-1 text-center">
        <i class="ni ni-fat-remove ni-3x"></i>
        <h4 class="heading mt-4">Please input DECLINE reason.</h4>
        <base-input v-model="declineModal.message" placeholder="Input decline reason"></base-input>
      </div>

      <template v-slot:footer>
        <base-button @click="submitDeclineModal" type="white">
          Proceed
        </base-button>
        <base-button type="link"
                     text-color="white"
                     class="ml-auto"
                     @click="cancelDeclineModal">
          Need more time to think...
        </base-button>
      </template>
    </modal>

    <!-- cancel modal -->
    <modal v-model:show="cancelModal.modal"
           modal-classes="modal-danger modal-dialog-centered">
      <div class="py-1 text-center">
        <i class="ni ni-fat-remove ni-3x"></i>
        <h4 class="heading mt-4">Please input CANCEL reason.</h4>
        <base-input v-model="cancelModal.message" placeholder="Input cancel reason"></base-input>
      </div>

      <template v-slot:footer>
        <base-button @click="submitCancelModal" type="white">
          Proceed
        </base-button>
        <base-button type="link"
                     text-color="white"
                     class="ml-auto"
                     @click="cancelCancelModal">
          Need more time to think...
        </base-button>
      </template>
    </modal>

    <!-- commission number modal -->
    <modal v-model:show="commissionNumberModal.modal"
           modal-classes="modal-info modal-dialog-centered">
      <div class="py-1 text-center">
        <i class="ni ni-badge ni-3x"></i>
        <h4 class="heading mt-4">Please input Bank Commission Number.</h4>
        <base-input type="number" v-model="commissionNumberModal.message" placeholder="Input Commission Number"></base-input>
      </div>

      <template v-slot:footer>
        <base-button @click="submitCommissionNumberModal" type="white">
          Proceed
        </base-button>
        <base-button type="link"
                     text-color="white"
                     class="ml-auto"
                     @click="cancelCommissionNumberModal">
          Need more time to think...
        </base-button>
      </template>
    </modal>
  </div>

  <div class="row" style="margin-top: 50px">
    <payment-details-table v-if="application.paymentDetails && application.paymentDetails.initPayment !== null" :application="application"/>
    <delivery-details-table v-if="application.deliveryDates && application.deliveryDates.scheduledAt !== null" :application="application"/>
  </div>
</template>
<script>
import DeliveryDetailsTable from "./Show/DeliveryDetailsTable";
import PaymentDetailsTable from "./Show/PaymentDetailsTable";

export default {
  name: "application-show-table",
  components: {
    DeliveryDetailsTable,
    PaymentDetailsTable,
  },
  props: {
    application: Object,
  },
  data() {
    return {
      declineModal: {
        modal: false,
        message: '',
      },
      cancelModal: {
        modal: false,
        message: '',
      },
      commissionNumberModal: {
        modal: false,
        message: '',
      },
      tableDate: [],
      type: 'light',
    };
  },
  computed: {
    isInactive() {
      return this.application.inactiveReason && this.application.inactiveReason.status !== null
    },
    isOwner() {
      return this.$store.getters['auth/isRoleOwner']
    },
    isUser() {
      return this.$store.getters['auth/isRoleUser']
    },
    progressBarColor() {
      if (this.application.applicationStage.level < 3) {
        return 'danger';
      } else if (this.application.applicationStage.level < 5) {
        return 'warning';
      } else if (this.application.applicationStage.level < 7) {
        return 'info';
      } else if (this.application.applicationStage.level < 9) {
        return 'primary';
      } else {
        return 'success';
      }
    },
    isDeclineAvailable() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.level < 7;
    },
    isCancelAvailable() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.level < 9;
    },
    isDraft() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'draft';
    },
    isProvideDriverLicense() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.step === 'provide_driver_license';
    },
    isQuoteAvailable() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'quote_available';
    },
    isCommissionNumberProvided() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.step === 'commission_number_provided';
    },
    isApproved() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.step === 'approved';
    },
    isContractPreparation() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'contract_preparation';
    },
    isCustomerSignIn() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'customer_sign_in';
    },
    isInitialPaymentProvided() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.step === 'initial_payment_provided';
    },
    isDeliveryBookingInProgress() {
      return !this.isInactive && this.isOwner && this.application.applicationStage && this.application.applicationStage.step === 'delivery_booking_in_progress';
    },
    isDeliveryBooked() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'delivery_booked';
    },
    isContractLive() {
      return !this.isInactive && this.isUser && this.application.applicationStage && this.application.applicationStage.step === 'contract_live';
    },
  },
  methods: {
    submitDeclineModal() {
      this.$emit('onDecline', this.declineModal.message)
      this.declineModal.modal = false
    },
    cancelDeclineModal() {
      this.declineModal.message = '';
      this.declineModal.modal = false
    },
    submitCancelModal() {
      this.$emit('onCancel', this.cancelModal.message);
      this.cancelModal.modal = false;
    },
    cancelCancelModal() {
      this.cancelModal.message = '';
      this.cancelModal.modal = false
    },
    submitCommissionNumberModal() {
      this.$emit('onProvideCommissionNumber', this.commissionNumberModal.message)
      this.commissionNumberModal.modal = false;
    },
    cancelCommissionNumberModal() {
      this.commissionNumberModal.message = '';
      this.commissionNumberModal.modal = false
    },
    toggleToLight() {
      this.type = 'light';
    },
    toggleToDark() {
      this.type = 'dark';
    },
  }
};
</script>

<style>
.link-custom {
  width: 110px;
  text-align: center
}
</style>
