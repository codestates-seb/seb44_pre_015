import { OrangeCircle } from '../questionwritehead/OrangeCircle.styled';
import { QTitle, QWrapper, Qinput, TContainer } from './QuestionTitle.styled';

export default function QuestionTitle(props) {
  const {value, onChange} = props;
  const maxLength = 80;
  

  return (
    <QWrapper>

      <TContainer>
        <OrangeCircle/>
          <QTitle>제목</QTitle>
      </TContainer>

        <Qinput
        type='text'
        placeholder='제목을 작성해 주세요. (80자 이하)'
        value={value}
        onChange={onChange}
        maxLength={maxLength}
      />
    </QWrapper>
  )
}
