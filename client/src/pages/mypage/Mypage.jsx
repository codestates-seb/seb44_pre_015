import {InfoContainer, TitleContainer, SubTitleContainer, Line, QuestionCardContainer} from './Mypage.style.jsx'
import {MainTitle, SubTitle} from '../../style/Title.styled'
import UserInfoMy from '../../components/user/UserInfoMy'
import FilterBtn from '../../components/filter/FilterBtn'
import QuestionCard from '../../components/card/QuestionCard'
import { InfoContainer, Line, QuestionCardContainer, SubTitleContainer, TitleContainer } from './Mypage.style'


export default function Mypage() {
  return (
    <InfoContainer>
      <TitleContainer>
        <MainTitle>My page</MainTitle>
      </TitleContainer>
      <UserInfoMy />

      <Line/>

      <FilterBtn/>

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
