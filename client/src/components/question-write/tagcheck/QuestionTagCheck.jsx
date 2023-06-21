import TagCheckBox from "../../tag/TagCheckBox"
import { useEffect, useState } from "react"
import { QuestionTitWrap, QuestionTit, QuestionText } from "./QuestionTagCheck.styled"

export default function QuestionTagCheck() {
  const [tags, setTags] = useState([]);
  const [checkCount, setCheckCount] = useState(0)

  const handlerTag = (name, description) => {
    let newTag = [...tags, { tagName: name, tagDescription: description }]
    let filterTag = tags.filter((el) => el.tagName !== name)
    let tagSelected = tags.some(el => el.tagName === name)

    if (tagSelected) {
      setTags(filterTag)
      setCheckCount(checkCount-1)
    } else {
      setTags(newTag)
      setCheckCount(checkCount+1)
    }
  }

  useEffect(() => {
    console.log(tags)
    console.log(checkCount)
  }, [tags])

  return (
    <>
      <QuestionTitWrap>
        <QuestionTit>태그 선택</QuestionTit>
        <QuestionText>최대 3개까지 선택 가능합니다</QuestionText>
      </QuestionTitWrap>
      <TagCheckBox handlerTag={handlerTag} tags={tags} checkCount={checkCount}/>
    </>
  )
}