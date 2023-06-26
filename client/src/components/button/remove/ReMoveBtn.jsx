import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { RemoveButtonWrap } from "./RemoveBtn.styled";

export default function ReMoveBtn({ questionId, memberInfo }) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const [memberId, setMemberId]= useState(1);
  const navigate = useNavigate();

  useEffect(()=>{
    if(memberInfo !== undefined) setMemberId(memberInfo.memberId);
  }, [memberInfo])

  const onClickHandler = async (e) => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if(memberId !== UID) return alert('작성자만 게시물을 삭제할 수 있습니다.');

    if(confirm('정말로 삭제하시겠습니까?') === true){
      const accessToken = JSON.parse(localStorage.getItem('accessToken'));

      await axios
        .delete(`${PROXY}/questions/${questionId}/${UID}`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': `Bearer ${accessToken}`,
          }
        })
      .then(res=> navigate('/'))
      .catch(err => navigate('/*'));
    }
  }

  return (
    <RemoveButtonWrap className="text-xs" onClick={onClickHandler}>삭제</RemoveButtonWrap>
  );
}