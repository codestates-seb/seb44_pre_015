import { AiOutlineCheck } from "react-icons/ai";
import { SelectionContainer, SelectionText } from './Selection.styled';

export default function Selection({ status }) {

  return (
    <SelectionContainer>
      <AiOutlineCheck style={{ color: status==='true' ? "#03B800" : "#DCDCDC" }} />
      <SelectionText status={status}>selection</SelectionText>
    </SelectionContainer>
  );
}
