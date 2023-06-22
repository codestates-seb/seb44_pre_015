import { useParams, useNavigate } from 'react-router-dom'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { DetailContainer, DeatilBottomSection } from './Detail.styled'
import QuestionSection from '../../components/question/question-section/QuestionSection'
import InputSection from '../../components/question/input-section/InputSection'
import CommentSection from '../../components/question/comment-section/CommentSection'
import RecommentSection from '../../components/question/recomment-section/RecommentSection'


export default function Detail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [datas, setDatas] = useState({});
  const [answers, setAnswers] = useState([]);

  useEffect(()=>{
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}/1`)
      .then(res => {
        setDatas(res.data);
        setAnswers(res.data.answers);
        console.log(res.data);
      })
      .catch(err => {
        console.log(err);
        navigate('/')  // 404 페이지로 추가 수정 필요
      });
  }, [])

  return (
    <DetailContainer>
      <QuestionSection title={datas.title} content={datas.detail} tags={datas.tags} createdAt={datas.createdAt} questionId={datas.questionId} memberId={datas.memberId} votesCount={datas.votesCount}/>
      <InputSection questionId={datas.questionId}/>

      <DeatilBottomSection>
        {
          answers.map(answer => <CommentSection key={answer.answerId} comment={answer.detail}/>)
        }
        <RecommentSection />
      </DeatilBottomSection>
    </DetailContainer>
  )
}
