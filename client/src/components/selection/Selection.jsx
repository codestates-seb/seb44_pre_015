import { AiOutlineCheck } from "react-icons/ai";
import { SelectionContainer, SelectionText } from './Selection.styled';

export default function Selection({ isChecked }) {
  return (
    <SelectionContainer>
      <AiOutlineCheck style={{ color: isChecked ? "#03B800" : "#DCDCDC" }} />
      <SelectionText>selection</SelectionText>
    </SelectionContainer>
  );
}
