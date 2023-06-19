import { MainPostCardContainer,CardTopSection, CardMiddleSection, CardBottomSection, TitleText, ContentText, Counters, CountContainer, CountText } from './MainPostCard.styled'
import UserInfoOwner from '../user/UserInfoOwner'
import Selection from '../selection/Selection'
import { AiFillHeart, AiFillEye } from 'react-icons/ai'
import { IoChatbubble } from 'react-icons/io5'
import Tags from '../tag/Tags'

export default function MainPostCard() {
  return (
    <MainPostCardContainer>
      <CardTopSection>
        <UserInfoOwner />
        <Selection />
      </CardTopSection>

      <CardMiddleSection>
        <TitleText>i want to question ... how to fix this?</TitleText>
        <ContentText>Lorem ipsum dolor sit amet consectetur adipisicing elit. Nesciunt, dolore impedit sequi exercitationem molestias deserunt quia ab nostrum quaerat, ratione dolorem sunt placeat quo obcaecati. Laborum vel sunt quas nihil!</ContentText>
      </CardMiddleSection>

      <CardBottomSection>
        <Counters>
          <CountContainer>
            <AiFillHeart style={{ color : "#CACACA"}}/>
            <CountText>00</CountText>
          </CountContainer>

          <CountContainer>
            <AiFillEye style={{ color : "#CACACA"}}/>
            <CountText>00</CountText>
          </CountContainer>

          <CountContainer>
            <IoChatbubble style={{ color : "#CACACA"}}/>
            <CountText>00</CountText>
          </CountContainer>
        </Counters>

        <Tags />
      </CardBottomSection>
    </MainPostCardContainer>
  )
}