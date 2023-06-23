import { useNavigate } from 'react-router-dom';
import { OrangeBtn } from "../Button.styled"

export default function LoginBtn(){
  const navigate = useNavigate();

  return (
    <OrangeBtn onClick={()=> navigate('/login')}>로그인</OrangeBtn>
  )
}