import { useRef } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RecommentInputContainer, Input, SubmitButton } from './RecommentInput.styled'

export default function RecommentInput({ answerId, memberId }) {
  const detailInput = useRef();
  const navigate = useNavigate();

  const onSubmitHandler = async (e) => {
    e.preventDefault();

    if ( detailInput.current.value === '') return alert('공백은 입력할 수 없습니다.')

    const body = {
      commentDetail: detailInput.current.value,
      answerId,
      memberId
    }

    await axios.post(
      "http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/comments",
      JSON.stringify(body),
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
    .then(res => window.location.reload())
    .catch(err => navigate('/*'));
  }

  return (
    <RecommentInputContainer>
      <form onSubmit={onSubmitHandler}>
        <Input ref={detailInput}/>
        <SubmitButton>댓글</SubmitButton>
      </form>
    </RecommentInputContainer>
  )
}