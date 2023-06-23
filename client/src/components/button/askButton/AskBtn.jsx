import { AskButton, AskContainer } from './AskBtn.styled';

export default function AskBtn({ onClick, buttonText = '질문하기' }) {
  return (
    <AskContainer>
      <AskButton onClick={onClick}>{buttonText}</AskButton>
    </AskContainer>
  )
}
