import AuthApi from './routes/auth.api';
import UserApi from './routes/user.api';
import VehicleApi from "./routes/vehicle.api";
import ApplicationApi from "./routes/application.api";

const api = {
  auth: new AuthApi(),
  user: new UserApi(),
  vehicle: new VehicleApi(),
  application: new ApplicationApi(),
};

export { api };
