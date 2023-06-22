import { AskButton, AskContainer } from './AskBtn.styled';

export default function AskBtn() {
  return (
    <AskContainer>
      <AskButton onClick={onClickHandler}>질문하기</AskButton>
    </AskContainer>
  )
}
