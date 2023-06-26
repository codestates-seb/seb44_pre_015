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
import { resetInput, typeTitle, typeDetail, checkPlusTags, checkMinusTags, updateTags } from '../../modules/questionSlice';

export default function Edit() {
  const navigate = useNavigate();
  const { questionId } = useParams();

  const title = useSelector((state) => state.question.title);
  const detail = useSelector((state) => state.question.detail);
  const tags = useSelector((state) => state.question.tags);
  const checkCount = useSelector((state) => state.question.checkedCount);

  const dispatch = useDispatch();

  const accessToken = JSON.parse(localStorage.getItem("accessToken"));
  const UID = JSON.parse(localStorage.getItem("UID"));

  const handleErrors = () => {
    navigate('/*');
  }

  const defaultHeaders = {
    'Content-Type': 'application/json;charset=UTF-8',
    'Authorization': `Bearer ${accessToken}`,
  }

  const handlerTag = (name, selectType) => {
    const newTag = { tagName: name, select: !selectType };
    const updatedTag = tags.map((el) => el.tagName === name ? newTag : el)

    dispatch(updateTags(updatedTag));
    tags.some((el) => el.tagName === name && selectType === true) ? dispatch(checkMinusTags()) : dispatch(checkPlusTags());
  };

  const titleChange = (e) => dispatch(typeTitle(e.target.value));

  const contentChange = (value) => dispatch(typeDetail(value));

  useEffect(() => {
    axios
      .get(`/questions/get/${questionId}/${UID}`)
      .then((res) => {
        const questionData = res.data;
        dispatch(typeTitle(questionData.title));
        dispatch(typeDetail(questionData.detail));
      })
      .catch(handleErrors);
  }, [dispatch, navigate, questionId]);

  useEffect(() => {
    axios
      .get(`/questions/get/patch/${questionId}/${UID}`)
      .then((res) => {
        const questionData = res.data;
        dispatch(updateTags(questionData.tags));
      })
      .catch(handleErrors);
  }, [dispatch, navigate, questionId]);


  const questionSubmit = () => {
    const selectedTags = tags.filter((el) => el.select).map((el) => ({ tagName: el.tagName }))

    const requestData = {
      title: title,
      detail: detail,
      memberId: UID,
      tags: selectedTags
    };

    axios
      .patch(`/questions/${questionId}/${UID}`, JSON.stringify(requestData), { headers: defaultHeaders })
      .then(() => {
        dispatch(resetInput());
        navigate(`/post/${questionId}/${UID}`);
      })
      .catch(handleErrors);
  };

  return (
    <EditContainer>
      <QuestionWriteHead headTitleText="질문 수정" />
      <QuestionTitle memTitle={title} onChange={titleChange} />
      <QuestionInput memDetail={detail} onChange={contentChange} />
      <QuestionTagCheck questionId={questionId} handlerTag={handlerTag} memtags={tags} checkCount={checkCount} />
      <AskBtn onClick={questionSubmit} buttonText="수정하기" />
    </EditContainer>
  );
}
