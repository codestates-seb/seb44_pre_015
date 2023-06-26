import TagCheckBox from "../../tag/TagCheckBox"
import { QuestionTitWrap, QuestionTit, QuestionText } from "./QuestionTagCheck.styled"

export default function QuestionTagCheck({ questionId, handlerTag, memtags, tags, checkCount, accessToken }) {
  return (
    <>
      <QuestionTitWrap>
        <QuestionTit>태그 선택</QuestionTit>
        <QuestionText>최대 3개까지 선택 가능합니다</QuestionText>
      </QuestionTitWrap>
      <TagCheckBox questionId={questionId} handlerTag={handlerTag} memtags={memtags} tags={tags} checkCount={checkCount} accessToken={accessToken} />
    </>
  )
}