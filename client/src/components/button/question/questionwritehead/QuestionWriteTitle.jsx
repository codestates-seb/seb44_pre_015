import { QuestionWrap, QuestionHead, QuestionHeadWrap, QuestionHeadtext } from "./QuestionWriteTitle.styled";
import { OrangeCircle } from "./OrangeCircle.styled.jsx";


export default function QuestionWriteTitle () {
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