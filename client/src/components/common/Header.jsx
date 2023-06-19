import { Link } from "react-router-dom";
import LoginBtn from '../button/login/LoginBtn'
import Logo from "../../assets/logo-stackoverflow.svg"
import { HeaderContainer, LogoContainer, LogoImg, Nav } from "../common/Header.styled"

export default function Header(){
  return (
    <HeaderContainer>
      <LogoContainer>
        <Link to="/">
          <LogoImg src={Logo} alt="logo" />
        </Link>
      </LogoContainer>
      <Nav>
        <LoginBtn />
      </Nav>
    </HeaderContainer>
  )
}