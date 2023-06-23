import { EditButtonWrap } from "./EditBtn.style";

export default function RecommentEditBtn({ setIsOpen, isOpen }) {
  const onClickHandler = () => {
    setIsOpen(!isOpen);
  }

  return (
    <EditButtonWrap className="text-xs"  onClick={onClickHandler}>수정</EditButtonWrap>
  );
}