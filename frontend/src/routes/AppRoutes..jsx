import { Routes, Route } from "react-router-dom";
import PropertyPage from "../features/property/pages/PropertyPage";
import PaymentPage from "../features/payment/pages/PaymentPage";
import LoginPage from "../features/auth/LoginPage";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<PropertyPage />} />
      <Route path="/payments" element={<PaymentPage />} />
      <Route path="/login" element={<LoginPage />} />
    </Routes>
  );
}

export default AppRoutes;