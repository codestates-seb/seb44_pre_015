import { useState } from 'react'
import axios from 'axios';
import UserInfoOther from "../../user/UserInfoOther"
import AnswerBtn from "../../button/answer/AnswerBtn"
import { InputFormContainer, AnswerForm, AnswerTextarea, AnswerBox } from "./InputSection.styled"

export default function InputSection({ questionId }) {
  const [comment, setComment] = useState('');

  const onSubmitHandler = async (e) => {
    e.preventDefault();
    if ( comment === '') {
      alert('공백은 입력할 수 없습니다.');
      return
    }

    const body = {
      questionId,
      memberId: 300,
      detail: comment
    }

    try {
      await axios.post(
        "https://56d7-59-11-30-105.ngrok-free.app/answers",
        JSON.stringify(body),
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": true,
          },
        }
      );
    } catch (error) {
      console.error(error);
    }
    
    return window.location.reload()
  }

  const onChangeHandler = e=> {
    setComment(e.target.value);
  }

  return (
    <InputFormContainer>

      <UserInfoOther />

      <AnswerForm onSubmit={onSubmitHandler}>
        <AnswerTextarea name="answer" placeholder="답변을 작성해 주세요." onChange={onChangeHandler}></AnswerTextarea>
        <AnswerBox>
          <AnswerBtn />
        </AnswerBox>
      </AnswerForm>

    </InputFormContainer>
  )
}