import OrangeCircle from '../questionwritehead/OrangeCircle'
import { TitleWrap, TitleText, QAInput } from './QuestionInput.styled'

export default function QuestionInput(props) {
  const { value, onChange } = props;

  return (
    <div>
      <TitleWrap>
        <OrangeCircle/>
        <TitleText>질문 내용</TitleText>
      </TitleWrap>

      <QAInput 
        placeholder='질문하실 내용을 작성해 주세요'
        value={value}
        onChange={onChange}
      />
    </div>
  )
}
