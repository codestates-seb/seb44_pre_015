import TagCheckBox from "../../tag/TagCheckBox"
import { useState } from "react"
import { QuestionTitWrap, QuestionTit, QuestionText } from "./QuestionTagCheck.styled"

export default function QuestionTagCheck() {
  const [tags, setTags] = useState([])

  const handlerTag = (name, description) => {
    let newTag = [...tags, { tagName: name, tagDescription: description }]
    let filterTag = tags.filter((el) => el.tagName !== name)

    if (tags.some(el => el.tagName === name)) {
      setTags(filterTag)
    } else {
      setTags(newTag)
    }
  }

  return (
    <>
      <QuestionTitWrap>
        <QuestionTit>태그 선택</QuestionTit>
        <QuestionText>최대 3개까지 선택 가능합니다</QuestionText>
      </QuestionTitWrap>
      <TagCheckBox handlerTag={handlerTag} tags={tags}/>
    </>
  )
}