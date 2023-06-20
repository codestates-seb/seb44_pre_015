import OrangeCircle from "./OrangeCircle"
import { QuestionHead, QuestionHeadWrap, QuestionWrap, QuestionHeadtext } from "./QuestionWriteHead.styled"

export default function QuestionWriteHead () {
    return(
        <QuestionHeadWrap>
            <QuestionHead>질문 작성</QuestionHead>
            <QuestionWrap>
            <OrangeCircle />
            <QuestionHeadtext>는 필수이며 없는 경우 선택 사항입니다.</QuestionHeadtext>
            </QuestionWrap>
        </QuestionHeadWrap>

    )
}