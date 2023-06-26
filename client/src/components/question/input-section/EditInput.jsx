import { useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AnswerBtn from "../../button/answer/AnswerBtn"
import { EditInputFormContainer, AnswerForm, AnswerEditTextarea, AnswerBox } from './InputSection.styled'

export default function EditInput({ comment, solutionStatus, answerId}) {
  const navigate = useNavigate();
  const answerInput = useRef();

  const onSubmitHandler = (e) => {
    e.preventDefault();
    
    const body = {
      answerId,
      detail: answerInput.current.value,
      solutionStatus,
    };

    const UID = JSON.parse(localStorage.getItem('UID'));
    const accessToken = JSON.parse(localStorage.getItem('accessToken'));

    axios.patch(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/answers/${answerId}/${UID}`, JSON.stringify(body), {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': `Bearer ${accessToken}`,
      }
    })
      .then(res => window.location.reload())
      .catch(err => navigate('/*'));
  }

  return (
    <EditInputFormContainer>
      <AnswerForm onSubmit={e => onSubmitHandler(e)}>
        <AnswerEditTextarea name="answer" placeholder="답변을 작성해 주세요." defaultValue={comment} ref={answerInput}/>
        <AnswerBox>
          <AnswerBtn />
        </AnswerBox>
      </AnswerForm>

    </EditInputFormContainer>
  )
}
