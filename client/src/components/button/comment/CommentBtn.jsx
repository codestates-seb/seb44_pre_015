import { CommentButton } from './CommentBtn.styled'

export default function CommentBtn({ setIsOpen }) {
  return (
    <CommentButton onClick={()=> setIsOpen(true)}>
      댓글쓰기
    </CommentButton>
  )
}