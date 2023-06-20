import tw from 'tailwind-styled-components'

import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead'
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle'
import QuestionInput from '../../components/question-write/question-input/QuestionInput'
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck'
import AskBtn from '../../components/button/askButton/AskBtn'

export const QuestionContainer = tw.div`
  my-5
  flex
  flex-col
  p-5
  gap-5
  items-center
`


export default function Question() {
  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle/>
      <QuestionInput />
      <QuestionTagCheck />
      <AskBtn />
    </QuestionContainer>
  )
}
