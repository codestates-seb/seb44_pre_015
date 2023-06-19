import { DetailContainer, DeatilBottomSection } from './Detail.styled'
import QuestionSection from '../../components/question/question-section/QuestionSection'
import InputSection from '../../components/question/input-section/InputSection'
import CommentSection from '../../components/question/comment-section/CommentSection'
import RecommentSection from '../../components/question/recomment-section/RecommentSection'



export default function Detail() {
  return (
    <DetailContainer>
      <QuestionSection />
      <InputSection />

      <DeatilBottomSection>
        <CommentSection />
        <RecommentSection />
      </DeatilBottomSection>
    </DetailContainer>
  )
}
