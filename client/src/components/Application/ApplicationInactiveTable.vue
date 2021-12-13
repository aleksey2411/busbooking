<template>
  <div class="card shadow" :class="type === 'dark' ? 'bg-default' : ''">
    <div
      class="card-header border-0"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0 text-red" :class="type === 'dark' ? 'text-white' : ''">
            {{ title }} ({{applications.length + 1}})
          </h3>
        </div>
        <div class="col text-right">
          <base-button style="width: 110px; text-align: center" v-if="type === 'dark'" @click="toggleToLight" type="primary" size="sm">Toggle to Light</base-button>
          <base-button style="width: 110px; text-align: center" v-else @click="toggleToDark" type="primary" size="sm">Toggle to Dark</base-button>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table v-if="applications"
                  class="table align-items-center table-flush"
                  :class="type === 'dark' ? 'table-dark' : ''"
                  :thead-classes="type === 'dark' ? 'thead-dark' : 'thead-light'"
                  tbody-classes="list"
                  :data="tableDate.length === 0 ? applications.slice(0, 10) : tableDate"
      >
        <template v-slot:columns>
          <th>Application Id</th>
          <th>Customer Name</th>
          <th>Broker Name</th>
          <th>Inactive Reason</th>
          <th>Canceled By</th>
          <th>Created At</th>
          <th>Inactivated At</th>
          <th></th>
        </template>

        <template v-slot:default="row">
          <th scope="row">
            <div class="media align-items-center">
              <div class="media-body">
                <span
                  class="name mb-0 text-sm nav-link"
                  style="cursor: pointer"
                  @click="$router.push({ name: 'application', params: { id: row.item.id } }).catch(() => {})"
                >
                  {{ row.item.applicationId }}
                </span>
              </div>
            </div>
          </th>
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
          <td class="budget">
            <badge class="large" type="danger">{{ row.item.inactiveReason.status }}</badge>
          </td>
          <td>
            <badge class="badge-dot mr-4" type="warning">
              <i :class="`bg-warning`"></i>
              <span class="status">{{ row.item.canceledBy.name }}</span>
            </badge>
          </td>
          <td class="budget">
            {{ row.item.createdAt }}
          </td>
          <td class="budget">
            {{ row.item.inactiveReason.inactivatedAt }}
          </td>
        </template>
      </base-table>
    </div>

    <div
      class="card-footer d-flex justify-content-end"
      :class="type === 'dark' ? 'bg-transparent' : ''"
    >
      <base-pagination
        :value="pagination.page"
        :per-page="pagination.perPage"
        :total="applications.length"
        @input="changeData"
      ></base-pagination>
    </div>
  </div>
</template>
<script>
export default {
  name: "application-inactive-table",
  props: {
    title: String,
    applications: Array,
  },
  data() {
    return {
      tableDate: [],
      type: 'light',
      pagination: {
        page: 1,
        perPage: 10
      }
    };
  },
  methods: {
    toggleToLight() {
      this.type = 'light';
    },
    toggleToDark() {
      this.type = 'dark';
    },
    changeData(pageNumber) {
      this.pagination.page = pageNumber;
      let startFrom = (this.pagination.page - 1) * this.pagination.perPage - 1;
      let endBy = startFrom + this.pagination.perPage;

      if (this.pagination.page === 1) {
        startFrom = 0
        endBy = 10;
      }

      this.tableDate = this.applications.slice(startFrom, endBy)
    }
  }
};
</script>
