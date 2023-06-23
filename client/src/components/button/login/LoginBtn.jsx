import { Link } from 'react-router-dom';
import { OrangeBtn } from "../Button.styled"
import axios from 'axios';

export default function LoginBtn(){

  return (
    <Link to="http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/login">
      <OrangeBtn >로그인</OrangeBtn>
    </Link>
    
  )
}