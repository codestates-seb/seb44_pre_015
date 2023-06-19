import { RecommentSectionContainer, TopSection, ReContainer, BottomSection, Recomment } from './RecommentSection.styled'
import UserInfoOwner from '../../user/UserInfoOwner'
import CommentBtn from '../../button/comment/CommentBtn'
import { BsArrowReturnRight } from 'react-icons/bs'

export default function RecommentSection() {
  return (
    <RecommentSectionContainer>
      <TopSection>
        <ReContainer>
          <BsArrowReturnRight style={{color : "#797979"}}/>
          <UserInfoOwner />
        </ReContainer>
      </TopSection>

      <BottomSection>
      <Recomment>Lorem ipsum dolor sit amet consectetur adipisicing elit. Placeat nobis excepturi dolor, exercitationem unde autem ipsum ut voluptas reiciendis ad vel repudiandae consectetur. Nulla ex dolores tempora minima, praesentium ducimus.</Recomment>

      <CommentBtn />
      </BottomSection>

    </RecommentSectionContainer>
  )
}
