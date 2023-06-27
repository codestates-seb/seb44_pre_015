import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { setSearch } from '../../modules/searchSlice';
import { Link } from "react-router-dom";
import LoginBtn from "../button/login/LoginBtn";
import Logo from "../../assets/logo-stackoverflow.svg";
import { HeaderContainer, LogoContainer, LogoImg, Nav, UserButton } from "../common/Header.styled";
import LogoutBtn from "../button/login/LogoutBtn";
import QuestionBtn from '../button/question/QuestionBtn';

export default function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [isLogIn, setIsLogin] = useState(false);
  const [check, setCheck] = useState();

  useEffect(() => {
    const parsedCheck = JSON.parse(localStorage.getItem('isLogIn'));
    if (parsedCheck !== null || parsedCheck !== undefined) setCheck(parsedCheck)
    else setCheck(false);
  }, [check]);

  useEffect(() => {
    if (check) {
      const parsedUserInfo = JSON.parse(localStorage.getItem('userInfo'));
      setIsLogin(true);
    }
  }, [check]);

  return (
    <HeaderContainer>
      <LogoContainer onClick={()=> dispatch(setSearch(''))}>
        <Link to="/">
          <LogoImg src={Logo} alt="logo" />
        </Link>
      </LogoContainer>
      <Nav>
        {isLogIn ? (
          <>
            <QuestionBtn />
            <LogoutBtn setIsLogin={setIsLogin} />
            <UserButton className="cursor-pointer" onClick={()=> navigate('/mypage')}>마이페이지</UserButton>
          </>
        ) : (
          <LoginBtn />
        )}
      </Nav>
    </HeaderContainer>
  );
}
