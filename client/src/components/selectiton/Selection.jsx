import tw from "tailwind-styled-components";
import { AiOutlineCheck } from "react-icons/ai";

const SelectionContainer = tw.div`
  flex
`;

const SelectionText = tw.span`
  ${({isChecked}) => ( "text-[#03B800]" || "text-[#DCDCDC]") }
`;

export default function Selection({ isChecked }) {
  return (
    <SelectionContainer>
      <AiOutlineCheck style={{ color: isChecked ? "#03B800" : "#DCDCDC" }} />
      <SelectionText>selection</SelectionText>
    </SelectionContainer>
  );
}
