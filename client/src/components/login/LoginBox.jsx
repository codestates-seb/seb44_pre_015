import { Link } from 'react-router-dom';
import { AiFillHeart, AiFillEye, AiOutlineCheck} from "react-icons/ai";
import { ImBubble } from "react-icons/im";
import {
  GlobalStyle, Loginbox, HeadText, Logintext,Wrap, IconWrap, GoogleBtnWrap, GoogleBtn, NaverBtnWrap, NaverBtn, LogoImg} from "./LoginBox.styled.jsx";
import googleimg from "../../assets/googleimg.svg";
import Naverimg from "../../assets/naverimg.svg";
import logo from "../../assets/logo-stackoverflow.svg";

const LoginBox = () => {
  return (
    <GlobalStyle>
      <Loginbox>
        <LogoImg src={logo}></LogoImg>
        <HeadText>Login the Stack Overflow community</HeadText>
        <Wrap>
          <Logintext>
            <IconWrap>
              <AiFillHeart />
            </IconWrap>
            Get unstuck — ask a question
          </Logintext>
          <Logintext>
            <IconWrap>
              <ImBubble />
            </IconWrap>
            Unlock new privileges like voting and commenting
          </Logintext>
          <Logintext>
            <IconWrap>
              <AiFillEye />
            </IconWrap>
            Save your favorite questions, answers,
            <br /> watch tags, and more
          </Logintext>
          <Logintext>
            <IconWrap>
              <AiOutlineCheck />
            </IconWrap>
            Earn reputation and badges
          </Logintext>
        </Wrap>

        <Link to="http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/login">
          <GoogleBtnWrap>
            <GoogleBtn src={googleimg}/>
            Sign up with Google
          </GoogleBtnWrap>
        </Link>

        <Link to="http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/login">
          <NaverBtnWrap>
            <NaverBtn src={Naverimg} />
            Sign up with Naver
          </NaverBtnWrap>
        </Link>
      </Loginbox>
    </GlobalStyle>
  );
};

export default LoginBox;
