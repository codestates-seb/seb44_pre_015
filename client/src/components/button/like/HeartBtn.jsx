import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { HeartButtonWrap } from "./HeartBtn.styled";
import { AiFillHeart } from 'react-icons/ai';

export default function HeartBtn({ votesCount, questionId, memberInfo, questionVoteByMember }) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const navigate = useNavigate();
  const [memberId, setMemberId]= useState(1);
  const [totalVoteCount, setTotalVoteCount] = useState(0);
  const [isVoted, setIsVoted]= useState(false);

  useEffect(()=>{
    if( memberInfo !== undefined ) setMemberId(memberInfo.memberId);
    if (votesCount !== undefined) setTotalVoteCount(votesCount);
    if (questionVoteByMember !== undefined) setIsVoted(questionVoteByMember);
  }, [memberInfo, votesCount, questionVoteByMember]);

  const onClickHandler = () => {
    const isLogIn = JSON.parse(localStorage.getItem('isLogIn'));
    if( !isLogIn ) return alert('회원만 좋아요를 누를 수 있습니다.');

    const accessToken = JSON.parse(localStorage.getItem('accessToken'));
    const UID = JSON.parse(localStorage.getItem('UID'));

    axios(`${PROXY}/questions/votes/${questionId}/${UID}`, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': `Bearer ${accessToken}`,
      }
    })
    .then(res => {
      setTotalVoteCount(res.data.totalVoteCount);
      setIsVoted(res.data.questionVoteStatus);
    })
    .catch(err => navigate('/*'));
  };

  return (
    <div>
      <HeartButtonWrap onClick={onClickHandler}>
        <AiFillHeart className={isVoted ? "text-orange-500" : "text-gray-300"} style={{fontSize : '13px'}}/>
      </HeartButtonWrap>
      <p className="ml-2.5 text-xs text-[#797979]">{ totalVoteCount }</p>
    </div>
  );
}
