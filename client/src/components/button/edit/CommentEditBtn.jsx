import { useEffect, useState } from 'react';
import { EditButtonWrap } from "./EditBtn.style";

export default function CommentEditBtn({ memberInfo, setEditIsOpen, editIsOpen }) {
  const [memberId, setMemberId] = useState();

  useEffect(()=>{
    if(memberInfo !== undefined) setMemberId(memberInfo.memberId);
  }, [memberInfo])

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( memberId !== UID) return alert('본인만 수정할 수 있습니다!');
    setEditIsOpen(!editIsOpen);
  }

  return (
    <EditButtonWrap className="text-xs" onClick={onClickHandler}>수정</EditButtonWrap>
  );
}