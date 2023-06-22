import OrangeCircle from '../questionwritehead/OrangeCircle';
import { TitleWrap, TitleText, EditorContainer, EditorContent } from './QuestionInput.styled';
import { useState } from 'react';
import 'react-quill/dist/quill.snow.css';

export default function QuestionInput(props) {
  const { value, onChange } = props;
  const [editorState, setEditorState] = useState('');

  const handleEditorChange = (content) => {
    setEditorState(content);
    onChange(content);
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
          value={editorState}
          onChange={handleEditorChange}
        />
      </EditorContainer>
    </div>
  );
}
