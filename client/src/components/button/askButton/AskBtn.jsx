import { AskButton, AskContainer } from './AskBtn.styled';

export default function AskBtn({onClick}) {
  return (
    <AskContainer>
      <AskButton onClick={onClick}>질문하기</AskButton>
    </AskContainer>
  )
}
