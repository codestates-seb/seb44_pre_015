import OrangeCircle from '../questionwritehead/OrangeCircle';
import { TitleWrap, TitleText, EditorContainer, EditorContent } from './QuestionInput.styled';
import { useDispatch, useSelector } from 'react-redux';
import 'react-quill/dist/quill.snow.css';
import { typeDetail } from '../../../modules/questionSlice';


export default function QuestionInput() {
  const detail = useSelector((state) => state.question.detail);
  const dispatch = useDispatch();

  const handleEditorChange = (content) => {
    dispatch(typeDetail(content));
  };

  return (
    <div>
      <TitleWrap>
        <OrangeCircle />
        <TitleText>질문 내용</TitleText>
      </TitleWrap>

      <EditorContainer>
        <EditorContent
          placeholder='질문하실 내용을 작성해 주세요'
          value={detail}
          onChange={handleEditorChange}
        />
      </EditorContainer>
    </div>
  );
}
