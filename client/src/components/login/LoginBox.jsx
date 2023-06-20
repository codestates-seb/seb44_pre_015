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
  GithubBtn,
} from "./LoginBox.styled.jsx";
import githubimg from "../../assets/githubimg.png";
import logo from "../../assets/logo-stackoverflow.sv`g";

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
        <GithubBtn>
          <img
            src={githubimg}
            style={{ display: "inline" }}
            className="githubimg"
            width="38px"
            alt="깃헙 로그인"
          />{" "}
          Sign up with Github
        </GithubBtn>
      </Loginbox>
    </GlobalStyle>
  );
};

export default LoginBox;
