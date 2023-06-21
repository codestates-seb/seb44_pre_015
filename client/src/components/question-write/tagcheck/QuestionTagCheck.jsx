import TagCheckBox from "../../tag/TagCheckBox"
import { useEffect, useState } from "react"
import { QuestionTitWrap, QuestionTit, QuestionText } from "./QuestionTagCheck.styled"

export default function QuestionTagCheck() {
  const [tag, setTag] = useState([]) 

  const handlerTag = () => {

  }

  return (
    <>
      <QuestionTitWrap>
        <QuestionTit>태그 선택</QuestionTit>
        <QuestionText>최대 3개까지 선택 가능합니다</QuestionText>
      </QuestionTitWrap>
      <TagCheckBox handlerTag={handlerTag} tag={tag} setTag={setTag} />
    </>
  )
}