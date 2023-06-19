import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import LoginBox from "./components/login/LoginBox";
import Selection from "./components/selectiton/Selection";

export default function App() {
  return (
    <BrowserRouter>
      <Header />

      <Routes>
        <Route path="/login" element={<LoginBox />}></Route>
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
