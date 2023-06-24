import { RemoveButtonWrap } from "./RemoveBtn.styled";

export default function CommentReMoveBtn({ memberId }) {
  
  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    if( memberId !== UID ) return alert('본인만 삭제할 수 있습니다!');
    if(confirm('정말 삭제하시겠습니까?') === true){
      
    }
  }

  return (
    <RemoveButtonWrap className="text-xs" onClick={onClickHandler} >삭제</RemoveButtonWrap>
  );
}