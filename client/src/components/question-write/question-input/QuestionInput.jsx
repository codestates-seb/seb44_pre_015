import OrangeCircle from '../questionwritehead/OrangeCircle'
import { TitleWrap, TitleText, QAInput } from './QuestionInput.styled'

export default function QuestionInput() {
  return (
    <div className='p-2'>
      <TitleWrap>
        <OrangeCircle/>
        <TitleText>질문 내용</TitleText>
      </TitleWrap>

      <QAInput placeholder='질문하실 내용을 작성해 주세요'/>

    </div>
  )
}
