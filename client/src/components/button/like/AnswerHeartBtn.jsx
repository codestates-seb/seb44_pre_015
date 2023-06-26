import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { HeartButtonWrap } from "./HeartBtn.styled";
import { AiFillHeart } from 'react-icons/ai';

export default function AnswerHeartBtn({votesCount, answerVoteByMember, answerId}) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const navigate = useNavigate();
  const [isVoted, setIsVoted]= useState(false);
  const [totalCount, setTotalCount]= useState(0);

  useEffect(()=> {
    setIsVoted(answerVoteByMember);
    setTotalCount(votesCount)
  }, [answerVoteByMember, votesCount])

  const onClickHandler = () => {
    
    const isLogIn = JSON.parse(localStorage.getItem('isLogIn'));
    if (!isLogIn) return alert('회원만 좋아요를 누를 수 있습니다.');
    
    const UID = JSON.parse(localStorage.getItem('UID'));
    const accessToken = JSON.parse(localStorage.getItem('accessToken'));
    
    axios(`${PROXY}/answers/votes/${answerId}/${UID}`, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': `Bearer ${accessToken}`,
      }
    })
    .then(res => {
      setIsVoted(res.data.answerVoteStatus);
      setTotalCount(res.data.totalVoteCount);
    })
    .catch(err => navigate('/*'));
  }

  return (
    <div>
      <HeartButtonWrap className='mt-3' onClick={onClickHandler}>
        <AiFillHeart className={isVoted ? "text-orange-500" : "text-gray-300"} style={{fontSize : '13px'}} />
      </HeartButtonWrap>
      <p className="ml-2.5 text-xs text-[#797979]">{totalCount}</p>
    </div>
  );
}
