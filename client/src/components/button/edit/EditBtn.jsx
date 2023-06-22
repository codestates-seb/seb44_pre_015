import { useNavigate } from 'react-router-dom';
import { EditButtonWrap } from "./EditBtn.style";

export default function EditBtn({ questionId }) {
  const navigate = useNavigate();

  const onClickHandler = () => {
    return navigate(`/edit/${questionId}`)
  }

  return (
    <EditButtonWrap className="text-xs" onClick={onClickHandler}>수정</EditButtonWrap>
  );
}