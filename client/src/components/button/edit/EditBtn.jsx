import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { EditButtonWrap } from "./EditBtn.style";

export default function EditBtn({ questionId, memberInfo }) {
  const navigate = useNavigate();
  const [memberId, setMemberId]= useState(1);

  useEffect(()=>{
    if(memberInfo !== undefined) setMemberId(memberInfo.memberId);
  }, [memberInfo])

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if(memberId !== UID) return alert('작성자만 게시물을 수정할 수 있습니다.')

    return navigate(`/edit/${questionId}`)
  }

  return (
    <EditButtonWrap className="text-xs" onClick={onClickHandler}>수정</EditButtonWrap>
  );
}