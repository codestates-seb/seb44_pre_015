import React from 'react';
import {
  MainPostCardContainer,
  CardTopSection,
  CardMiddleSection,
  CardBottomSection,
  TitleText,
  ContentText,
  Counters,
  CountContainer,
  CountText,
} from './MainPostCard.styled';
import UserInfoOwner from '../user/UserInfoOwner';
import Selection from '../selection/Selection';
import { AiFillHeart, AiFillEye } from 'react-icons/ai';
import { IoChatbubble } from 'react-icons/io5';
import Tags from '../tag/Tags';

export default function MainPostCard({
  title,
  content,
  votesCount,
  viewCount,
  answerCount,
  createdAt,
  memberId,
  tags,
}) {
  return (
    <MainPostCardContainer>
      <CardTopSection>
        <UserInfoOwner createdAt={createdAt} memberId={memberId} />
        <Selection />
      </CardTopSection>

      <CardMiddleSection>
        <TitleText>{title}</TitleText>
        <ContentText>{content}</ContentText>
      </CardMiddleSection>

      <CardBottomSection>
        <Counters>
          <CountContainer>
            <AiFillHeart style={{ color: '#CACACA' }} />
            <CountText>{votesCount}</CountText>
          </CountContainer>

          <CountContainer>
            <AiFillEye style={{ color: '#CACACA' }} />
            <CountText>{viewCount}</CountText>
          </CountContainer>

          <CountContainer>
            <IoChatbubble style={{ color: '#CACACA' }} />
            <CountText>{answerCount}</CountText>
          </CountContainer>
        </Counters>

        <Tags tags={tags} />
      </CardBottomSection>
    </MainPostCardContainer>
  );
}
