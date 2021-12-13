import { getAuth } from '../configuration/instance';

const LIST = '/api/vehicle/list';
const COUNT_AVAILABLE = '/api/vehicle/count/available';
const BOOKED = '/api/vehicle/booked';
const SHOW = (vehicleId) => `/api/vehicle/show/${vehicleId}`

export default class VehicleApi {
    list() {
        return getAuth(LIST);
    }

    countAvailable() {
        return getAuth(COUNT_AVAILABLE);
    }

    booked() {
        return getAuth(BOOKED);
    }

    show(vehicleId) {
        return getAuth(SHOW(vehicleId));
    }
}
