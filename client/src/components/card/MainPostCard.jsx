import { useState } from 'react'
import { MainPostCardContainer,CardTopSection, CardMiddleSection, CardBottomSection, TitleText, ContentText, Counters, CountContainer, CountText } from './MainPostCard.styled'
import UserInfoOwner from '../user/UserInfoOwner'
import Selection from '../selection/Selection'
import { AiFillHeart, AiFillEye } from 'react-icons/ai'
import { IoChatbubble } from 'react-icons/io5'
import Tags from '../tag/Tags'

export default function MainPostCard({ title, detail, viewCount, votesCount, status, answerCount }) {
  const [text, setText] = useState(detail.replace(/(<([^>]+)>)/gi, ''))

  return (
    <MainPostCardContainer>
      <CardTopSection>
        <UserInfoOwner />
        <Selection status={status}/>
      </CardTopSection>

      <CardMiddleSection>
        <TitleText>{ title }</TitleText>
        <ContentText>{ text.length > 200 ? text.slice(0,200) + '...' : text }</ContentText>
      </CardMiddleSection>

      <CardBottomSection>
        <Counters>
          <CountContainer>
            <AiFillHeart style={{ color : "#CACACA"}}/>
            <CountText>{ votesCount  }</CountText>
          </CountContainer>

          <CountContainer>
            <AiFillEye style={{ color : "#CACACA"}}/>
            <CountText>{ viewCount }</CountText>
          </CountContainer>

          <CountContainer>
            <IoChatbubble style={{ color : "#CACACA"}}/>
            <CountText>{ answerCount }</CountText>
          </CountContainer>
        </Counters>

        <Tags />
      </CardBottomSection>
    </MainPostCardContainer>
  )
}