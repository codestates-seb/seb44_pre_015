import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { RecommentInputContainer, EditInput, EditButton } from './RecommentInput.styled'

export default function RecommentEditInput({ commentId, memberInfo, commentDetail }) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const navigate = useNavigate();
  const [memberId, setMemberId] = useState(1);
  const detailInput = useRef();

  useEffect(()=>{
    if( memberInfo !== undefined ) setMemberId(memberInfo.memberId);
    console.log(memberInfo);
  }, [memberInfo])

  const onSubmitHandler = (e) => {
    e.preventDefault();

    const UID = JSON.parse(localStorage.getItem('UID'));
    if( UID !== memberId ) return alert('댓글 작성자만 댓글을 수정할 수 있습니다.')

    const accessToken = JSON.parse(localStorage.getItem('accessToken'));
    const body = {
      commentId,
      commentDetail: detailInput.current.value
    };

    axios.patch(`${PROXY}/comments/${commentId}/${UID}`, JSON.stringify(body), {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': `Bearer ${accessToken}`,
      }
    })
      .then(res => window.location.reload())
      .catch(err => navigate('/*'))
  }

  return (
    <RecommentInputContainer>
      <form onSubmit={e => onSubmitHandler(e)}>
        <EditInput ref={detailInput} defaultValue={commentDetail}/>
        <EditButton>댓글</EditButton>
      </form>
    </RecommentInputContainer>
  )
}