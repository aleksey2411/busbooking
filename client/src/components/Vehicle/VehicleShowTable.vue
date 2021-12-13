<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 v-if="vehicle.isBooked">
            Individual Vehicle. {{vehicle.vid}}
            <badge type="info">Booked</badge> <br/>
            This vehicle is booked for application
            <span
              class="text-info"
              style="cursor: pointer"
              @click="$router.push({ name: 'application', params: { id: vehicle.relatedApplication.id } }).catch(() => {})"
            >
              {{vehicle.relatedApplication.applicationId}}
            </span>
          </h3>
          <h3 v-else class="mb-0" :class="type === 'dark' ? 'text-white' : ''">
            Individual Vehicle. {{vehicle.vid}}
          </h3>
        </div>
        <div class="col text-right">
          <base-button style="width: 110px; text-align: center" v-if="userCouldLease" @click="openCreateApplicationModal" type="success" size="sm">Lease Vehicle</base-button>
          <base-button style="width: 110px; text-align: center" v-if="type === 'dark'" @click="toggleToLight" type="primary" size="sm">Toggle to Light</base-button>
          <base-button style="width: 110px; text-align: center" v-else @click="toggleToDark" type="primary" size="sm">Toggle to Dark</base-button>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table v-if="[vehicle]"
                  class="table align-items-center table-flush"
                  :class="type === 'dark' ? 'table-dark' : ''"
                  :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
                  tbody-classes="list"
                  :data="[vehicle]"
      >
        <template v-slot:columns>
          <th>Corporate Description</th>
          <th>Base Price</th>
          <th>Paint Cost Metallic</th>
          <th>Paint Cost Solid</th>
          <th>Delivery Charge</th>
          <th>Vehicle Type</th>
          <th>Vehicle Registration</th>
        </template>

        <template v-slot:default="row">
          <td>
            <badge class="badge-dot mr-4" type="success">
              <i :class="`bg-info`"></i>
              <span class="status">{{ row.item.corporateDescription }}</span>
            </badge>
          </td>
          <td class="budget">
            $ {{ row.item.basePrice }} USD
          </td>
          <td class="budget">
            + $ {{ row.item.paintCostMetallic }} USD
          </td>
          <td class="budget">
            + $ {{ row.item.paintCostSolid }} USD
          </td>
          <td class="budget">
            + $ {{ row.item.deliveryCharge }} USD
          </td>
          <td v-if="row.item.vehicleType" class="budget">
            {{ row.item.vehicleType.title }}
          </td>
          <td v-if="row.item.vehicleType" class="budget">
            <badge v-if="row.item.vehicleType.registered === 'yes'" class="large" type="info">{{ row.item.vehicleType.registered }}</badge>
            <badge v-else type="danger">{{ row.item.vehicleType.registered }}</badge>
          </td>
        </template>
      </base-table>
    </div>
  </div>
  <modal v-model:show="createApplication.modal"
         body-classes="p-0"
         modal-classes="modal-dialog-centered modal-sm">
    <card type="secondary" shadow
          header-classes="bg-white pb-5"
          body-classes="px-lg-5 py-lg-3"
          class="border-0">
      <div class="text-center text-muted mb-4">
        <small>Lets clarify all deals details</small>
      </div>
      <div class="text-center mb-4">
        <p>Final price: <span class="text-green">{{createApplication.finalPrice}}</span></p>
      </div>

      <form role="form">
        <hr class="my-4" />
        <base-checkbox @click="includePaintSolid" :disabled="createApplication.paintCost.solidDisabled">
          Paint Solid
        </base-checkbox>
        <base-checkbox @click="includePaintMetallic" :disabled="createApplication.paintCost.metallicDisabled">
          Paint Metalic
        </base-checkbox>

        <hr class="my-4" />
        <base-checkbox @click="includeDeliveryCheckbox">
          Include Delivery
        </base-checkbox>
        <div class="text-center">
          <base-button
            @click="ApplicationCreate"
            type="success"
            class="my-4">
            Lease Vehicle!
          </base-button>
        </div>
      </form>
    </card>
  </modal>
</template>
<script>
export default {
  name: "vehicle-show-table",
  props: {
    title: String,
    vehicle: Object,
  },
  data() {
    return {
      type: 'light',
      createApplication: {
        modal: false,
        paintCost: {
          solidDisabled: false,
          metallicDisabled: false,
        },
        deliveryInclude: false,
        finalPrice: 0,
      }
    };
  },
  computed: {
    userCouldLease() {
      return this.$store.getters['auth/isRoleUser'] && !this.vehicle.isBooked && !this.$store.getters['user/hasActiveApplication']
    }
  },
  methods: {
    openCreateApplicationModal() {
      this.createApplication.modal = true;
      this.createApplication.finalPrice = parseFloat(this.vehicle.basePrice)
    },
    includeDeliveryCheckbox(e) {
      if (e.target.checked === undefined) {
        return;
      }

      if (e.target.checked === true) {
        this.createApplication.deliveryInclude = true;
        this.createApplication.finalPrice = this.getFloatsSum(this.createApplication.finalPrice, this.vehicle.deliveryCharge);
      } else {
        this.createApplication.deliveryInclude = false;
        this.createApplication.finalPrice = this.getFloatsDif(this.createApplication.finalPrice, this.vehicle.deliveryCharge);
      }
    },
    includePaintSolid(e) {
      if (e.target.checked === undefined) {
        return;
      }

      if (e.target.checked === true) {
        this.createApplication.finalPrice = this.getFloatsSum(this.createApplication.finalPrice, this.vehicle.paintCostSolid);
        this.createApplication.paintCost.metallicDisabled = true;
      } else {
        this.createApplication.finalPrice = this.getFloatsDif(this.createApplication.finalPrice, this.vehicle.paintCostSolid);
        this.createApplication.paintCost.metallicDisabled = false;
      }
    },
    includePaintMetallic(e) {
      if (e.target.checked === undefined) {
        return;
      }

      if (e.target.checked === true) {
        this.createApplication.finalPrice = this.getFloatsSum(this.createApplication.finalPrice, this.vehicle.paintCostMetallic);
        this.createApplication.paintCost.solidDisabled = true;
      } else {
        this.createApplication.finalPrice = this.getFloatsDif(this.createApplication.finalPrice, this.vehicle.paintCostMetallic);
        this.createApplication.paintCost.solidDisabled = false;
      }
    },
    getFloatsSum(a, b) {
      return Math.round((parseFloat(a) + parseFloat(b)) * 100) / 100;
    },
    getFloatsDif(a, b) {
      return Math.round((parseFloat(a) - parseFloat(b)) * 100) / 100;
    },
    ApplicationCreate() {
      this.$emit('onApplicationCreate', {
        'finalPrice': this.createApplication.finalPrice,
        'deliveryIncluded': this.createApplication.deliveryInclude
      });
      this.createApplication.modal = false;
      this.$store.commit('user/userHasApplication');
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
