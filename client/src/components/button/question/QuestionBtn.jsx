import { useNavigate } from 'react-router-dom'
import { OrangeBtn } from "../Button.styled"

export default function QuestionBtn() {
  const navigate = useNavigate();

  return (
    <OrangeBtn onClick={()=> navigate('/question')}>질문하기</OrangeBtn>
  )
}