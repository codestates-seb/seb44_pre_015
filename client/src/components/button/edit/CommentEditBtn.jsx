import { useEffect, useState } from 'react';
import { EditButtonWrap } from "./EditBtn.style";

export default function CommentEditBtn({ memberInfo }) {
  const [memberId, setMemberId] = useState(1);

  useEffect(()=>{
    if(memberInfo !== undefined) setMemberId(memberInfo.memberId);
  }, [])

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( memberId !== UID) return alert('본인만 수정할 수 있습니다!');
  }

  return (
    <EditButtonWrap className="text-xs" onClick={onClickHandler}>수정</EditButtonWrap>
  );
}