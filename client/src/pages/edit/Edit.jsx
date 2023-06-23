import { useSelector, useDispatch } from 'react-redux';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
import { EditContainer } from './Edit.styled';
import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead';
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle';
import QuestionInput from '../../components/question-write/question-input/QuestionInput';
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck';
import AskBtn from '../../components/button/askButton/AskBtn';
import { useNavigate } from 'react-router-dom';
import { resetInput, typeTitle, typeDetail, typeTags, checkPlusTags, checkMinusTags, updateTags } from '../../modules/questionSlice';

export default function Edit() {
  const navigate = useNavigate();
  const { questionId } = useParams();

  const title = useSelector((state) => state.question.title);
  const detail = useSelector((state) => state.question.detail);
  const tags = useSelector((state) => state.question.tags);
  const checkCount = useSelector((state) => state.question.checkedCount);

  const dispatch = useDispatch();

  const handlerTag = (name, description) => {
    const tagSelected = tags.some((el) => el.tagName === name);

    if (tagSelected) {
      const updatedTags = tags.filter((el) => el.tagName !== name);
      dispatch(updateTags(updatedTags));
      dispatch(checkMinusTags());
    } else {
      const newTag = { tagName: name, tagDescription: description };
      const updatedTags = [...tags, newTag];
      dispatch(updateTags(updatedTags));
      dispatch(checkPlusTags());
    }
  };

  const titleChange = (e) => {
    dispatch(typeTitle(e.target.value));
  };

  const contentChange = (value) => {
    dispatch(typeDetail(value));
  };

  useEffect(() => {
    axios
      .get(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/get/${questionId}/1`)
      .then((res) => {
        const questionData = res.data;
        dispatch(typeTitle(questionData.title));
        dispatch(typeDetail(questionData.detail));

        const newTags = questionData.tags.map((tag) => ({ tagName: tag }));
        dispatch(updateTags(newTags));
      })
      .catch((err) => {
        console.log(err);
        navigate('/');
      });
  }, [dispatch, navigate, questionId]);

  const questionSubmit = () => {
    const UID = localStorage.getItem('UID');
  
    const requestData = {
      title: title,
      detail: detail,
      memberId: UID,
      tags: tags.map((tag) => tag.tagName),
    };
    const headers = {
      'Content-Type': 'application/json',
    };
  
    axios
      .patch(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/get/${questionId}/1`, requestData, { headers })
      .then((response) => {
        const updatedQuestion = response.data;
        dispatch(typeTitle(updatedQuestion.title));
        dispatch(typeDetail(updatedQuestion.detail));
        navigate('/');
      })
      .catch((error) => {
        console.log('에러:', error);
        navigate('/');
      });
  };
  

  return (
    <EditContainer>
      <QuestionWriteHead />
      <QuestionTitle memTitle={title} onChange={titleChange} />
      <QuestionInput memDetail={detail} onChange={contentChange} />
      <QuestionTagCheck memTags={tags} handlerTag={handlerTag} tags={tags} checkCount={checkCount} />
      <AskBtn onClick={questionSubmit} buttonText="수정하기" />
    </EditContainer>
  );
}
