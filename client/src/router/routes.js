import Layout from "../layout/Layout";
import Login from "../views/Auth/Login";
import Register from "../views/Auth/Register";
import Home from "../views/Home";
import Booking from "../views/Booking/Booking";
import HelpLayout from "../layout/HelpLayout";
import Contacts from "../views/Help/Contacts";
import Partners from "../views/Help/Partners";
import Terms from "../views/Help/Terms";
import Drivers from "../views/Help/Drivers";
import Carriers from "../views/Help/Carriers";
import UserHome from "../views/Home/UserHome";
import Order from "../views/Booking/Order";
import Logout from "../views/Auth/Logout";
import AdminHome from "../views/Home/AdminHome";
import AdminTables from "../views/Home/AdminTables";

const routes = [
  {
    path: "/",
    redirect: "home",
    name: "layout",
    component: Layout,
    children: [
      {
        path: "/login",
        name: "login",
        components: { default: Login },
        meta: {
          anonymousOnly: true,
        },
      },
      {
        path: "/logout",
        name: "logout",
        components: { default: Logout },
      },
      {
        path: "/register",
        name: "register",
        components: { default: Register },
      },
      {
        path: "/home",
        name: "home",
        components: { default: Home },
      },
      {
        path: "/booking",
        name: "booking",
        components: { default: Booking },
      },
      {
        path: "/user-tickets",
        name: "user-tickets",
        components: { default: UserHome },
      },
      {
        path: "/admin-tickets",
        name: "admin-home",
        components: { default: AdminHome },
      },
      {
        path: "/admin-tables",
        name: "admin-tables",
        components: { default: AdminTables },
      },
      {
        path: "/order",
        name: "order",
        components: { default: Order },
      },
    ],
  },
  {
    path: "/help",
    name: "help-layout",
    component: HelpLayout,
    children: [
      {
        path: "/contacts",
        name: "contacts",
        components: { default: Contacts },
      },
      {
        path: "/partners",
        name: "partners",
        components: { default: Partners },
      },
      {
        path: "/terms",
        name: "terms",
        components: { default: Terms },
      },
      {
        path: "/for-drivers",
        name: "drivers",
        components: { default: Drivers },
      },
      {
        path: "/for-carriers",
        name: "carriers",
        components: { default: Carriers },
      },
    ],
  },
];

export default routes;
