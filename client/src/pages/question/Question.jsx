import { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { QuestionContainer } from './Question.styled';
import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead';
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle';
import QuestionInput from '../../components/question-write/question-input/QuestionInput';
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck';
import AskBtn from '../../components/button/askButton/AskBtn';
import { resetInput, typeTitle, typeDetail, typeTags, checkPlusTags, checkMinusTags, updateTags } from '../../modules/questionSlice';

export default function Question() {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const title = useSelector((state) => state.question.title);
  const detail = useSelector((state) => state.question.detail);
  const tags = useSelector((state) => state.question.tags);
  const checkCount = useSelector(state => state.question.checkedCount);
  const accessToken = JSON.parse(localStorage.getItem("accessToken"));
  const UID = JSON.parse(localStorage.getItem("UID"));

  useEffect(()=>{
    dispatch(resetInput());
  }, [])

  const handlerTag = (name) => {
    const tagSelected = tags.some((el) => el.tagName === name);

    if (tagSelected) {
      const updatedTags = tags.filter((el) => el.tagName !== name);
      dispatch(updateTags(updatedTags));
      dispatch(checkMinusTags())
    } else {
      const newTag = { tagName: name };
      dispatch(typeTags(newTag));
      dispatch(checkPlusTags())
    }
    
  };

  const titleChange = (e) => dispatch(typeTitle(e.target.value));

  const contentChange = (value) => dispatch(typeDetail(value));

  const questionSubmit = () => {

    const requestData = {
      title: title,
      detail: detail,
      memberId: UID,
      tags: tags,
    };

    const headers = {
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': `Bearer ${accessToken}`
    };

    axios.post(
      `${PROXY}/questions/register`, requestData, { headers })
      .then(() => {
        clearInput();
        navigate('/');
      })
      .catch(() => {
        navigate('/*');
      })
  };

  const clearInput = () => {
    dispatch(resetInput());
  };

  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle value={title} onChange={titleChange} />
      <QuestionInput value={detail} onChange={contentChange} />
      <QuestionTagCheck accessToken={accessToken} handlerTag={handlerTag} tags={tags} checkCount={checkCount} />
      <AskBtn onClick={questionSubmit} />
    </QuestionContainer>
  );
}
