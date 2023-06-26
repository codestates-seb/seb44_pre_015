import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Tags from '../tag/Tags';
import { CardContent, CardTitle, CardWrapper, SelectWrapper} from './QuestionCard.styled';
import Selection from '../selection/Selection';

export default function QuestionCard({ title, detail, questionId, solutionStatus
}) {
  const navigate = useNavigate();
  const [content, setContent] = useState('');
  
  useEffect(()=> {
    setContent(detail.replace(/(<([^>]+)>)/gi, ''));
  }, [detail])

  const onClickHandler = () => {
    const UID = JSON.parse(localStorage.getItem('UID'));
    navigate(`/post/${questionId}/${UID}`)
  }

  return (
    <>
    <CardWrapper onClick={onClickHandler}>
      <Tags />
      <CardTitle>{ title }</CardTitle>
      <CardContent>{ content.length >= 75 ? content.slice(0, 75) + '...' : content} </CardContent>

      <div className='absolute right-3 bottom-2 max-sm:bottom-3'>
      <Selection status={solutionStatus.toString()} />
      </div>
    </CardWrapper>
    </>
  )
}
