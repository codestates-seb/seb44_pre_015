import { useState } from 'react';
import { RecommentSectionContainer, TopSection, ReContainer, BottomSection, Recomment } from './RecommentSection.styled'
import RecommentEditBtn from '../../button/edit/RecommentEditBtn';
import RecommentEditInput from '../../question/comment-section/RecommentEditInput'
import RecommentRemoveBtn from '../../button/remove/RecommentRemoveBtn'
import UserInfoOwner from '../../user/UserInfoOwner'
import { BsArrowReturnRight } from 'react-icons/bs'

export default function RecommentSection({ commentDetail, memberInfo, createdAt, commentId }) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <RecommentSectionContainer>
      <TopSection>
        <ReContainer>
          <BsArrowReturnRight style={{color : "#797979"}}/>
          <UserInfoOwner createdAt={createdAt} memberInfo={memberInfo}/>
        </ReContainer>

        <div className='flex gap-3'>
          <RecommentEditBtn setIsOpen={setIsOpen} isOpen={isOpen} />
          <RecommentRemoveBtn memberInfo={memberInfo} commentId={commentId}/>
        </div>
      </TopSection>

      <BottomSection>
      <Recomment>{commentDetail}</Recomment>
      {isOpen && <RecommentEditInput commentDetail={commentDetail} memberInfo={memberInfo} commentId={commentId} />}
      </BottomSection>

    </RecommentSectionContainer>
  )
}