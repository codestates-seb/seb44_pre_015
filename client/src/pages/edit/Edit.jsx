import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { EditContainer } from './Edit.styled'
import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead'
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle'
import QuestionInput from '../../components/question-write/question-input/QuestionInput'
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck'
import AskBtn from '../../components/button/askButton/AskBtn'

export default function Edit() {
  const { questionId } = useParams();
  const [data, setData] = useState([]);

  useEffect(()=> {
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/${questionId}/1`)
    .then(res => {
      setData(res.data);
      console.log(res.data);
    })
    .catch(err => {
      console.log(err);
      navigate('/*');
    });
  }, [])

  return (
    <EditContainer>
      <QuestionWriteHead />
      <QuestionTitle title={data.title}/>
      <QuestionInput content={data.detail}/>
      <QuestionTagCheck />
      <AskBtn />
    </EditContainer>
  )
}