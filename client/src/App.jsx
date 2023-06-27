import { BrowserRouter, Routes, Route } from "react-router-dom";
import axios from 'axios';
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import Login from './pages/login/Login'
import Main from './pages/main/Main'
import Search from './pages/search/Search';
import Detail from './pages/detail/Detail'
import Question from './pages/question/Question'
import Edit from './pages/edit/Edit';
import Mypage from './pages/mypage/Mypage';
import NotFound from './pages/notfound/NotFound'

export default function App() {
  return (
    <BrowserRouter>
      <Header />

      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/search/:searchWord' element={<Search />} />
        <Route path="/login" element={<Login />} />
        <Route path='/post/:questionId/:memberId' element={<Detail />} />
        <Route path='/question' element={<Question />}/>
        <Route path='/edit/:questionId' element={<Edit />}/>
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/*" element={<NotFound />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  );
}
