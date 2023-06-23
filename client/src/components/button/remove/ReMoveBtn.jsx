import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RemoveButtonWrap } from "./RemoveBtn.styled";

export default function ReMoveBtn({ questionId, memberId }) {
  const navigate = useNavigate();

  const onClickHandler = async (e) => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if(memberId !== UID) return alert('작성자만 게시물을 삭제할 수 있습니다.');

    if(confirm('정말로 삭제하시겠습니까?') === true){
      const accessToken = JSON.parse(localStorage.getItem('accessToken'));

      await axios
        .delete(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/${questionId}/${UID}`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': `Bearer ${accessToken}`,
          }
        })
      .then(res=> console.log(res))
      .catch(err => console.log(err))
      await navigate('/');
    }
    return
  }

  return (
    <RemoveButtonWrap className="text-xs" onClick={onClickHandler}>삭제</RemoveButtonWrap>
  );
}