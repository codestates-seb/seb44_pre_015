import axios from 'axios';
import { useEffect, useState } from 'react';
import { SelectionBtnContainer, SelectionBtnText } from './SelectionBtn.styled';
import { AiOutlineCheck } from "react-icons/ai";

export default function SelectionBtn({ solutionStatus, answerId, writer }) {
  const [writerId, setWriterId] = useState(1);

  useEffect(()=>{
    if (writer !== undefined) setWriterId(writer);
  }, [writer])

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    console.log(writerId, UID)
    if(UID !== writer) return alert('작성자만 답변 채택이 가능합니다.');

    axios.get(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/answers/selection/${answerId}/${UID}`)
    .then(res => console.log(res));
  }

  return (
    <SelectionBtnContainer solutionStatus={solutionStatus} onClick={onClickHandler}>
      <AiOutlineCheck color={ solutionStatus ? '#03B800' : '#DCDCDC'} />
      <SelectionBtnText solutionStatus={solutionStatus}>selection</SelectionBtnText>
    </SelectionBtnContainer>
  )
}
