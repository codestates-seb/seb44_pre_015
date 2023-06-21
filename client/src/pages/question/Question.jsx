import { useState } from 'react';
import axios from 'axios';
import { QuestionContainer } from './Question.styled';
import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead';
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle';
import QuestionInput from '../../components/question-write/question-input/QuestionInput';
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck';
import AskBtn from '../../components/button/askButton/AskBtn';
import { useNavigate } from 'react-router-dom';

export default function Question() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [tags, setTags] = useState([]);
  const [checkCount, setCheckCount] = useState(0);
  const navigate = useNavigate();

  const handlerTag = (name, description) => {
    const tagSelected = tags.some((el) => el.tagName === name);

    if (tagSelected) {
      const updatedTags = tags.filter((el) => el.tagName !== name);
      setTags(updatedTags);
      setCheckCount((prevCount) => prevCount - 1);
    } else {
      const newTag = { tagName: name, tagDescription: description };
      setTags((prevTags) => [...prevTags, newTag]);
      setCheckCount((prevCount) => prevCount + 1);
    }
  };

  const titleChange = (e) => {
    setTitle(e.target.value);
  };

  const contentChange = (e) => {
    setContent(e.target.value);
  };

  const questionSubmit = () => {
    const requestData = {
      title: title,
      detail: content,
      memberId: 1,
      tags: [1, 2, 3]
    };

    const headers = {
      'Content-Type': 'application/json',
      'ngrok-skip-browser-warning': true,
    };

    axios
    .post('https://56d7-59-11-30-105.ngrok-free.app/questions/register', requestData, { headers })
    .then((response) => {
      clearInput();
      navigate('/');
    })
    .catch((error) => {
      console.log('에러:', error);
    });
  };

  const clearInput = () => {
    setTitle('');
    setContent('');
    setTags([]);
    setCheckCount(0);
  };

  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle value={title} onChange={titleChange} />
      <QuestionInput value={content} onChange={contentChange} />
      <QuestionTagCheck handlerTag={handlerTag} tags={tags} checkCount={checkCount} />
      <AskBtn onClick={questionSubmit} />
    </QuestionContainer>
  );
}
