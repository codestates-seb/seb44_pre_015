import React, { useState } from 'react';
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
  const navigate = useNavigate();

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

    const submittedTitle = requestData.title; 
    const submittedDetail = requestData.detail; 

    axios
      .post('https://56d7-59-11-30-105.ngrok-free.app/questions/register', requestData, { headers })
      .then((response) => {
        console.log('질문 등록 성공');
        console.log('등록된 질문 정보:');
        console.log(JSON.stringify({
          title: submittedTitle,
          detail: submittedDetail,
          ...response.data
        }, null, 2));

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
  };



  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle value={title} onChange={titleChange} />
      <QuestionInput value={content} onChange={contentChange} />
      <QuestionTagCheck />
      <AskBtn onClick={questionSubmit} />
    </QuestionContainer>
  );
}
