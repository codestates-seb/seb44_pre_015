import Logo from "../../assets/logo-stackoverflow.svg"
import { HeaderContainer, LogoContainer, LogoImg, Nav, LoginBtn } from "../common/Header.styled"

const Header = () => {
  return (
    <HeaderContainer>
      <LogoContainer>
        <a href="#">
          <LogoImg src={Logo} alt="logo" />
        </a>
      </LogoContainer>
      <Nav>
        <LoginBtn>로그인</LoginBtn>
      </Nav>
    </HeaderContainer>
  )
}

export default Header