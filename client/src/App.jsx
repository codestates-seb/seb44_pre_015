import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Main from './pages/main/Main';
import LoginBox from "./components/login/LoginBox";

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path="/login" element={<LoginBox />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
