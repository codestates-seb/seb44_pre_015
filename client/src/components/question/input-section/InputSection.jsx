import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import UserInfoOther from "../../user/UserInfoOther"
import AnswerBtn from "../../button/answer/AnswerBtn"
import { InputFormContainer, AnswerForm, AnswerTextarea, AnswerBox } from "./InputSection.styled"

export default function InputSection({ questionId }) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const navigate= useNavigate();
  const [comment, setComment] = useState('');

  const onSubmitHandler = async (e) => {
    e.preventDefault();
    if ( comment === '') return alert('공백은 입력할 수 없습니다.');

    const isLogIn = JSON.parse(localStorage.getItem('isLogIn'));
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( isLogIn !== true ) return alert('로그인을 해주세요!');

    const body = {
      questionId,
      memberId: UID,
      detail: comment
    }

    const accessToken = JSON.parse(localStorage.getItem('accessToken'));

    try {
      await axios.post(
        `${PROXY}/answers`,
        JSON.stringify(body),
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': `Bearer ${accessToken}`,
          },
        }
      );
    } catch (error) {
      navigate('/*')
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