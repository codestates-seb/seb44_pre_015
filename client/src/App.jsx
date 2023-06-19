import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Login from './pages/login/Login'
import Main from './pages/main/Main'

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      
      
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path="/login" element={<Login />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
