import axios from 'axios';
import { useEffect, useState } from 'react';
import { HeartButtonWrap } from "./HeartBtn.styled";
import { AiFillHeart } from 'react-icons/ai';

export default function HeartBtn({ votesCount, questionId, memberInfo, questionVoteByMember }) {
  const [memberId, setMemberId]= useState(1);

  useEffect(()=>{
    if( memberInfo !== undefined ) setMemberId(memberInfo.memberId);
  }, [memberInfo]);

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( memberId !== UID ) return alert('회원만 좋아요를 누를 수 있습니다.')

    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/votes/${questionId}/${UID}`)
    .then(res => window.location.reload())
    .catch(err => console.log(err));
  };

  return (
    <div>
      <HeartButtonWrap onClick={onClickHandler}>
        <AiFillHeart className={questionVoteByMember ? "text-orange-500" : "text-gray-300"} style={{fontSize : '13px'}}/>
      </HeartButtonWrap>
      <p className="ml-2.5 text-xs text-[#797979]">{ votesCount }</p>
    </div>
  );
}
