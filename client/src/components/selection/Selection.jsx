import { AiOutlineCheck } from "react-icons/ai";
import { SelectionContainer, SelectionText } from './Selection.styled';

export default function Selection({ solutionStatus }) {
  return (
    <SelectionContainer>
      <AiOutlineCheck style={{ color: solutionStatus ? "#03B800" : "#DCDCDC" }} />
      <SelectionText solutionStatus={solutionStatus}>selection</SelectionText>
    </SelectionContainer>
  );
}
