import React from 'react';
import { AiFillHeart  } from 'react-icons/ai';
import { ImBubble } from 'react-icons/im';
import { AiFillEye } from 'react-icons/ai';
import { AiOutlineCheck } from 'react-icons/ai';
import { GlobaStyle, Loginbox, HeadText, Logintext, Wrap, IconWrap, GithubBtn, SignLine, SignText } from "./LoginBox.styled.jsx";
import tw from "tailwind-styled-components";
import githubimg from "../../../assets/image 26.png";
import logo from "../../../assets/logos_stackoverflow.png";



const LoginBox = () => {
    return (
        <GlobaStyle>
        <Loginbox>
            <img src={logo} width="180px"></img>
            <HeadText>Login the Stack Overflow community</HeadText>
           <Wrap>
            <Logintext><IconWrap><AiFillHeart /></IconWrap>Get unstuck — ask a question</Logintext>
            <Logintext><IconWrap><ImBubble /></IconWrap>Unlock new privileges like voting and commenting</Logintext>
            <Logintext><IconWrap><AiFillEye /></IconWrap>Save your favorite questions, answers,<br /> watch tags, and more</Logintext>
            <Logintext><IconWrap><AiOutlineCheck /></IconWrap>Earn reputation and badges</Logintext>
             </Wrap>
               <SignText>Sign up</SignText>
            <SignLine />
            <GithubBtn>
               <img src={githubimg} style={{display:"inline"}}className="githubimg" width="38px" alt="깃헙 로그인" /> Sign up with Github
            </GithubBtn>
        </Loginbox>
        </GlobaStyle>
    )  
}

export default LoginBox;