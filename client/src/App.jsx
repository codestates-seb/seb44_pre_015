import { BrowserRouter, Routes, Route } from "react-router-dom";
import axios from 'axios';
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Login from './pages/login/Login'
import Main from './pages/main/Main'
import Detail from './pages/detail/Detail'
import Question from './pages/question/Question'
import Mypage from './pages/mypage/Mypage';

export default function App() {

  const getApi = async () => {
    await axios('https://39c3-59-11-30-105.ngrok-free.app/questions/recent?page=0&size=10',{
      headers: {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "69420",
      },
    }
    ).then(res => console.log(res.data));
  }

  getApi()

  return (
    <BrowserRouter>
      <Header />


      <Routes>
        <Route path='/' element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path='/post:id' element={<Detail />} />
        <Route path='/question' element={<Question />}/>
        <Route path="/mypage" element={<Mypage />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
