import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RemoveButtonWrap } from "./RemoveBtn.styled";

export default function CommentReMoveBtn({ memberInfo, commentId }) {
  const navigate = useNavigate();

  const onClickHandler = async () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( UID !== memberInfo.memberId) return alert('본인만 삭제 가능합니다!');

    const accessToken = JSON.parse(localStorage.getItem('accessToken'));
    
    await axios.delete(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/comments/${commentId}/${UID}`,
      {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': `Bearer ${accessToken}`,
        }
      })
      .then(res=> window.location.reload())
      .catch(err => navigate('/*'))
  }

  return (
    <RemoveButtonWrap className="text-xs" onClick={onClickHandler}>삭제</RemoveButtonWrap>
  );
}