import tw from 'tailwind-styled-components'
import QuestionSection from '../../components/question/question-section/QuestionSection'
import InputSection from '../../components/question/input-section/InputSection'
import CommentSection from '../../components/question/comment-section/CommentSection'
import RecommentSection from '../../components/question/recomment-section/RecommentSection'

export const DetailContainer = tw.div`
  flex
  flex-col
  items-center
  gap-y-10
`

export default function Detail() {
  return (
    <DetailContainer>
      <QuestionSection />
      <InputSection />
      <CommentSection />
      <RecommentSection />
    </DetailContainer>
  )
}
