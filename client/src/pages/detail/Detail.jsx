import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { DetailContainer, DeatilBottomSection } from './Detail.styled'
import QuestionSection from '../../components/question/question-section/QuestionSection'
import InputSection from '../../components/question/input-section/InputSection'
import CommentSection from '../../components/question/comment-section/CommentSection'
import RecommentSection from '../../components/question/recomment-section/RecommentSection'


export default function Detail() {
  const { id } = useParams();
  const [datas, setDatas] = useState({});
  const [answers, setAnswers] = useState([]);

  useEffect(()=>{
    axios(`https://56d7-59-11-30-105.ngrok-free.app/questions/${id}/1`,  {
      headers: {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "69420",
      }
    })
      .then(res => {
        setDatas(res.data);
        setAnswers(res.data.answers);
        console.log(res.data);
      })
      .catch(err => console.log(err));
  }, [])

  return (
    <DetailContainer>
      <QuestionSection title={datas.title} content={datas.detail} tags={datas.tags} createdAt={datas.createdAt}/>
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
