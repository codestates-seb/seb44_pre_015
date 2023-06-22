import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';
import { QuestionContainer } from './Question.styled';
import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead';
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle';
import QuestionInput from '../../components/question-write/question-input/QuestionInput';
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck';
import AskBtn from '../../components/button/askButton/AskBtn';
import { useNavigate } from 'react-router-dom';
import { resetInput, typeTitle, typeDetail, typeTags , checkPlusTags, checkMinusTags, updateTags} from '../../modules/questionSlice';

export default function Question() {
  const navigate = useNavigate();
  const title = useSelector((state) => state.question.title);
  const detail = useSelector((state) => state.question.detail);
  const tags = useSelector((state) => state.question.tags);
  const checkCount = useSelector(state => state.question.checkedCount)

  const dispatch = useDispatch();

  const handlerTag = (name, description) => {
    const tagSelected = tags.some((el) => el.tagName === name);

    if (tagSelected) {
      const updatedTags = tags.filter((el) => el.tagName !== name);
      dispatch(updateTags(updatedTags));
      dispatch(checkMinusTags())
    } else {
      const newTag = { tagName: name, tagDescription: description };
      dispatch(typeTags(newTag));
      dispatch(checkPlusTags())
    }
  };

  const titleChange = (e) => {
    dispatch(typeTitle(e.target.value));
  };

  const contentChange = (value) => {
    dispatch(typeDetail(value));
  };

  const questionSubmit = () => {
    const requestData = {
      title: title,
      detail: detail,
      memberId: 1,
      tags: [1, 2, 3],
    };

    const headers = {
      'Content-Type': 'application/json',
      'ngrok-skip-browser-warning': true,
    };

    axios
      .post('http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/register', requestData, { headers })
      .then((response) => {
        clearInput();
        navigate('/');
      })
      .catch((error) => {
        console.log('에러:', error);
      });
  };

  const clearInput = () => {
    dispatch(resetInput());
  };

  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle value={title} onChange={titleChange} />
      <QuestionInput value={detail} onChange={contentChange} />
      <QuestionTagCheck handlerTag={handlerTag} tags={tags} checkCount={checkCount} />
      <AskBtn onClick={questionSubmit} />
    </QuestionContainer>
  );
}
