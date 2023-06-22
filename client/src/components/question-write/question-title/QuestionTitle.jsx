import { typeTitle } from '../../../modules/questionSlice';
import OrangeCircle from '../questionwritehead/OrangeCircle';
import { QTitle, QWrapper, QInputContainer, QInput } from './QuestionTitle.styled';
import { useDispatch, useSelector } from 'react-redux';

export default function QuestionTitle() {
  const title = useSelector((state) => state.question.title);
  const dispatch = useDispatch();

  const handleChange = (e) => {
    dispatch(typeTitle(e.target.value));
  };

  const maxLength = 80;

  return (
    <QWrapper>
      <QInputContainer>
        <OrangeCircle />
        <QTitle>제목</QTitle>
      </QInputContainer>

      <QInput
        type='text'
        placeholder='제목을 작성해 주세요. (80자 이하)'
        value={title}
        onChange={handleChange}
        maxLength={maxLength}
      />
    </QWrapper>
  );
}
