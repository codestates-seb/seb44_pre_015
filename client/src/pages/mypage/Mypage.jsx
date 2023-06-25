import { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {InfoContainer, TitleContainer, SubTitleContainer, Line, QuestionCardContainer} from './Mypage.style.jsx'
import {MainTitle, SubTitle} from '../../style/Title.styled'
import UserInfoMy from '../../components/user/UserInfoMy'
import QuestionCard from '../../components/card/QuestionCard'

export default function Mypage() {
  const navigate = useNavigate();
  const [userInfo, setUser] = useState('');

  useEffect(()=>{
    const UID = JSON.parse(localStorage.getItem('UID'));
    const accessToken = JSON.parse(localStorage.getItem('accessToken'));

    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/members/${UID}`, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': `Bearer ${accessToken}`,
      }
    })
      .then(res => {
        setUser(res.data);
        console.log(res.data);
      })
      .catch(err => navigate('/*'))
  }, [])

  return (
    <InfoContainer>
      <TitleContainer>
        <MainTitle>My page</MainTitle>
      </TitleContainer>
      <UserInfoMy picture={userInfo.picture} userName={userInfo.userName} total={userInfo.questions}/>

      <Line/>

      <SubTitleContainer>
        <SubTitle>Questions</SubTitle>
      </SubTitleContainer>

      <QuestionCardContainer>
        {
          userInfo !== '' && userInfo.questions.map(
            question => <QuestionCard key={question.questionId} title={question.title} detail={question.detail} questionId={question.questionId} solutionStatus={question.solutionStatus} />)
        }
      </QuestionCardContainer>

    </InfoContainer>
  )
}
