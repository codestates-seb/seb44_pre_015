import { CommentButton } from './CommentBtn.styled'

export default function CommentBtn({ setIsOpen, isOpen }) {

  const onClickHandler = () => {
    const isLogIn = JSON.parse(localStorage.getItem('isLogIn'));
    if (!isLogIn) return alert('회원만 댓글을 작성할 수 있습니다.')
    setIsOpen(!isOpen);
  }

  return (
    <CommentButton onClick={onClickHandler}>
      댓글쓰기
    </CommentButton>
  )
}