import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { setSearch } from '../../modules/searchSlice';
import { Link } from "react-router-dom";
import LoginBtn from "../button/login/LoginBtn";
import Logo from "../../assets/logo-stackoverflow.svg";
import { HeaderContainer, LogoContainer, LogoImg, Nav } from "../common/Header.styled";
import LogoutBtn from "../button/login/LogoutBtn";
import { UserImgSm, Img } from "../user/UserCommon.styled";
import QuestionBtn from '../button/question/QuestionBtn';

export default function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [isLogIn, setIsLogin] = useState(false);
  const [userInfo, setUserInfo]= useState('');


  useEffect(()=> {
    const check = JSON.parse(localStorage.getItem('isLogIn'));
    if ( check ){
      setIsLogin(true);
      const userInfo = JSON.parse(localStorage.getItem('userInfo'));
      setUserInfo(userInfo);
    }
  }, [isLogIn])

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
            <UserImgSm className="cursor-pointer" onClick={()=> navigate('/mypage')}>
              <Img
                src={userInfo === null ? `https://www.google.com/url?sa=i&url=https%3A%2F%2Fnamu.wiki%2Fw%2F%25EA%25B3%25A0%25EC%2596%2591%25EC%259D%25B4&psig=AOvVaw2QeI-SJMfLdtM2dLn8fw90&ust=1687583039014000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCMCjy-_O2P8CFQAAAAAdAAAAABAD` : userInfo.picture }
                alt="userImg"
              />
            </UserImgSm>
          </>
        ) : (
          <LoginBtn />
        )}
      </Nav>
    </HeaderContainer>
  );
}
