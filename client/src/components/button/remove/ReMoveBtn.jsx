import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RemoveButtonWrap } from "./RemoveBtn.styled";

export default function ReMoveBtn({ questionId, memberId }) {
  const navigate = useNavigate();

  const onClickHandler = async (e) => {
    if(confirm('정말로 삭제하시겠습니까?') === true){
      await axios
        .delete(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/${questionId}/${memberId}`,
        {
          headers: {
            "Content-Type": "application/json",
          }
        })
      .then(res=> console.log(res))
      await navigate('/');
    }
    return
  }

  return (
    <RemoveButtonWrap className="text-xs" onClick={onClickHandler}>삭제</RemoveButtonWrap>
  );
}