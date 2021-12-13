import { getAuth, postAuth } from "../configuration/instance";

const DECLINE = "/api/application/decline";
const CANCEL = "/api/application/cancel";
const PROVIDE_DRIVER_LICENSE = "/api/application/workflow/provide-driver-license";
const SUBMIT_QUOTE = "/api/application/workflow/submit-quote";
const PROVIDE_COMMISSION_NUMBER = "/api/application/workflow/provide-commission-number";
const APPROVE = "/api/application/workflow/approve";
const PREPARE_CONTRACT = "/api/application/workflow/prepare-contract";
const SIGNING_CONTRACT = "/api/application/workflow/signing-contract";
const PROVIDE_INITIAL_PAYMENT = "/api/application/workflow/provide-initial-payment";
const SCHEDULE_DELIVERY = "/api/application/workflow/schedule-delivery";
const CONFIRM_DELIVERY = "/api/application/workflow/confirm-delivery";
const DELIVERY_PASSED = "/api/application/workflow/delivery-passed";

const CITIES = "/api/cities/";
const ROUTES_BY_CITIES = (arrivalCityId, departureCityId) => `/api/routes/${arrivalCityId}/${departureCityId}`;
const TRIPS_BY_ROUTE = (routeId, date) => `/api/trips/routes/${routeId}/${date}`;
const BUY_TICKET = "/api/tickets/";
const TRIP_BY_ID = (tripId) =>  `/api/trips/${tripId}`;
const BUS_STOP_BY_ROUTE = (routeId) => `/api/routes/bus-stops/${routeId}`;
const TICKETS_BY_USER_ID = '/api/tickets/users/';
const TICKETS_TODAY = '/api/tickets/';
const REJECT_TICKET = (ticketId) =>  `/api/tickets/${ticketId}/reject`;
const CONFIRM_TICKET = (ticketId) =>  `/api/tickets/${ticketId}/confirm`;

export default class ApplicationApi {
  todaysTickets() {
    return getAuth(TICKETS_TODAY);
  }

  confirmTicket(ticketId) {
    return getAuth(CONFIRM_TICKET(ticketId))
  }

  rejectTicket(ticketId) {
    return getAuth(REJECT_TICKET(ticketId))
  }

  ticketsByUser() {
    return getAuth(TICKETS_BY_USER_ID);
  }

  trip(tripId) {
    return getAuth(TRIP_BY_ID(tripId))
  }

  busStop(routeId) {
    return getAuth(BUS_STOP_BY_ROUTE(routeId))
  }

  cities() {
    return getAuth(CITIES);
  }

  routesByCities(arrivalCityId, departureCityId) {
    return getAuth(ROUTES_BY_CITIES(arrivalCityId, departureCityId));
  }

  tripsByRoute(routeId, date) {
    return getAuth(TRIPS_BY_ROUTE(routeId, date));
  }

  buyTicket(tripId, routeBusStopId, numberOfSeats) {
    return postAuth(BUY_TICKET, {
      "numberOfSeats": numberOfSeats,
      "routeBusStopDTO": {
        "id": routeBusStopId,
      },
      "tripDTO": {
        "id": tripId,
      },
    });
  }

  cancel(id, message) {
    return postAuth(CANCEL, { "applicationId": id, "cancelReason": message });
  }

  provideDriverLicense(id) {
    return postAuth(PROVIDE_DRIVER_LICENSE, { "applicationId": id });
  }

  submitQuote(id) {
    return postAuth(SUBMIT_QUOTE, { "applicationId": id });
  }

  provideCommissionNumber(id, commissionNumber) {
    return postAuth(PROVIDE_COMMISSION_NUMBER, { "applicationId": id, "commissionNumber": commissionNumber });
  }

  approve(id) {
    return postAuth(APPROVE, { "applicationId": id });
  }

  prepareContract(id) {
    return postAuth(PREPARE_CONTRACT, { "applicationId": id });
  }

  signingContract(id) {
    return postAuth(SIGNING_CONTRACT, { "applicationId": id });
  }

  provideInitialPayment(id) {
    return postAuth(PROVIDE_INITIAL_PAYMENT, { "applicationId": id });
  }

  scheduleDelivery(id) {
    return postAuth(SCHEDULE_DELIVERY, { "applicationId": id });
  }

  confirmDelivery(id) {
    return postAuth(CONFIRM_DELIVERY, { "applicationId": id });
  }

  deliveryPassed(id) {
    return postAuth(DELIVERY_PASSED, { "applicationId": id });
  }

  decline(id, message) {
    return postAuth(DECLINE, { "applicationId": id, "declineReason": message });
  }
}
