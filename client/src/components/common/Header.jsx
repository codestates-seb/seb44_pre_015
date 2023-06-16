import Logo from "../../assets/logo-stackoverflow.svg"
import { HeaderContainer, LogoContainer, LogoImg, Nav, LoginBtn } from "../common/Header.styled"
import { Link } from "react-router-dom";


const Header = () => {
  return (
    <HeaderContainer>
      <LogoContainer>
        <Link to="/">
          <LogoImg src={Logo} alt="logo" />
        </Link>
      </LogoContainer>
      <Nav>
        <LoginBtn>로그인</LoginBtn>
      </Nav>
    </HeaderContainer>
  )
}

export default Header