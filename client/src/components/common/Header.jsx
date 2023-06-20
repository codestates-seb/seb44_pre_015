import { Link } from "react-router-dom";
import LoginBtn from "../button/login/LoginBtn";
import Logo from "../../assets/logo-stackoverflow.svg";
import { HeaderContainer, LogoContainer, LogoImg, Nav } from "../common/Header.styled";
import LogoutBtn from '../button/login/LogoutBtn';
import { Img, UserImgSm } from '../user/UserCommon.styled';
import QuestionBtn from '../button/question/QuestionBtn';

export default function Header({isLoggedIn}) {

  return (
    <HeaderContainer>
      <LogoContainer>
        <Link to="/">
          <LogoImg src={Logo} alt="logo" />
        </Link>
      </LogoContainer>
      <Nav>
        {isLoggedIn ? (
          <>
            <QuestionBtn />
            <LogoutBtn />
            <UserImgSm>
            <Img
          src="https://velog.velcdn.com/images/crg1050/profile/c180a703-e4c1-4c72-a014-9b7f0f3787a4/image.JPG"
          alt="userImg" />
            </UserImgSm>
          </>
        ) : (
          <LoginBtn />
        )}
      </Nav>
    </HeaderContainer>
  );
}
