import {InfoContainer, TitleContainer, SubTitleContainer, Line, QuestionCardContainer} from './Mypage.style.jsx'
import {MainTitle, SubTitle} from '../../style/Title.styled'
import UserInfoMy from '../../components/user/UserInfoMy'
import MyFilterBtn from '../../components/filter/MyFilterBtn.jsx'
import QuestionCard from '../../components/card/QuestionCard'

export default function Mypage() {
  return (
    <InfoContainer>
      <TitleContainer>
        <MainTitle>My page</MainTitle>
      </TitleContainer>
      <UserInfoMy />

      <Line/>

      <MyFilterBtn />

      <SubTitleContainer>
        <SubTitle>Questions</SubTitle>
      </SubTitleContainer>

      <QuestionCardContainer>
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
        <QuestionCard />
      </QuestionCardContainer>

    </InfoContainer>
  )
}
