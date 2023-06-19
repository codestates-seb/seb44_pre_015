import UserInfoOther from "../../user/UserInfoOther"
import { InputFormContainer, AnswerForm, AnswerTextarea, AnswerBox, AnswerButton } from "./InputSection.styled"

const handlePaintAnswer = (event) => {
  event.preventDefault()
}

export default function InputSection() {
  return (
    <InputFormContainer>

      <UserInfoOther />

      <AnswerForm onSubmit={handlePaintAnswer}>
        <AnswerTextarea name="answer" placeholder="답변을 작성해 주세요."></AnswerTextarea>
        <AnswerBox>
          <AnswerButton>등록</AnswerButton>
        </AnswerBox>
      </AnswerForm>

    </InputFormContainer>
  )
}