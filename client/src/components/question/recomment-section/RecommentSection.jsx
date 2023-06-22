import { RecommentSectionContainer, TopSection, ReContainer, BottomSection, Recomment } from './RecommentSection.styled'
import UserInfoOwner from '../../user/UserInfoOwner'
import { BsArrowReturnRight } from 'react-icons/bs'

export default function RecommentSection({ commentDetail }) {
  return (
    <RecommentSectionContainer>
      <TopSection>
        <ReContainer>
          <BsArrowReturnRight style={{color : "#797979"}}/>
          <UserInfoOwner />
        </ReContainer>
      </TopSection>

      <BottomSection>
      <Recomment>{commentDetail}</Recomment>
      </BottomSection>

    </RecommentSectionContainer>
  )
}
