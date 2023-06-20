import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Login from './pages/login/Login'
import Main from './pages/main/Main'
import Detail from './pages/detail/Detail'
import Question from './pages/question/Question'

export default function App() {
  return (
    <BrowserRouter>
      <Header />

      <Routes>
        <Route path='/' element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path='/post:id' element={<Detail />} />
        <Route path='/question' element={<Question />}/>
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
