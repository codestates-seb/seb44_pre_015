import OrangeCircle from '../questionwritehead/OrangeCircle';
import { QTitle, QWrapper, QInputContainer, QInput } from './QuestionTitle.styled';

export default function QuestionTitle(props) {
  const { value, onChange } = props;
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
        value={value}
        onChange={onChange}
        maxLength={maxLength}
      />
    </QWrapper>
  );
}
