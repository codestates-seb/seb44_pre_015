import { Link } from 'react-router-dom';
import { AiFillHeart } from "react-icons/ai";
import { ImBubble } from "react-icons/im";
import { AiFillEye } from "react-icons/ai";
import { AiOutlineCheck } from "react-icons/ai";
import {
  GlobalStyle,
  Loginbox,
  HeadText,
  Logintext,
  Wrap,
  IconWrap,
  GoogleBtn,
  NaverBtn
} from "./LoginBox.styled.jsx";
import googleimg from "../../assets/googleimg.svg";
import Naverimg from "../../assets/naverimg.svg";
import logo from "../../assets/logo-stackoverflow.svg";

const LoginBox = () => {
  return (
    <GlobalStyle>
      <Loginbox>
        <img src={logo} width="180px"></img>
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
          <GoogleBtn>
            <img
              src={googleimg}
              style={{ display: "inline" }}
              className="googleimg"
              width="28px"
              alt="구글 로그인"
            />{" "}
            Sign up with Google
          </GoogleBtn>
        </Link>

        <Link to="http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/login">
          <NaverBtn>
            <img
              src={Naverimg}
              style={{ display: "inline" }}
              className="naverimg"
              width="19px"
              alt="네이버 로그인"
            />{" "}
            Sign up with Naver
          </NaverBtn>
        </Link>
      </Loginbox>
    </GlobalStyle>
  );
};

export default LoginBox;
