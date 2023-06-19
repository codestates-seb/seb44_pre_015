import UserInfoOther from "../../user/UserInfoOther"
import AnswerBtn from "../../button/answer/AnswerBtn"
import { InputFormContainer, AnswerForm, AnswerTextarea, AnswerBox } from "./InputSection.styled"

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
          <AnswerBtn />
        </AnswerBox>
      </AnswerForm>

    </InputFormContainer>
  )
}