import tw from 'tailwind-styled-components'

import QuestionWriteHead from '../../components/question-write/questionwritehead/QuestionWriteHead'
import QuestionTitle from '../../components/question-write/question-title/QuestionTitle'
import QuestionInput from '../../components/question-write/question-input/QuestionInput'
import QuestionTagCheck from '../../components/question-write/tagcheck/QuestionTagCheck'

export const QuestionContainer = tw.div`
  flex
  flex-col
  p-5
  gap-5
`


export default function Question() {
  return (
    <QuestionContainer>
      <QuestionWriteHead />
      <QuestionTitle/>
      <QuestionInput />
      <QuestionTagCheck />
    </QuestionContainer>
  )
}
