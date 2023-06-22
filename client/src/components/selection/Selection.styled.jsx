import tw from "tailwind-styled-components";

export const SelectionContainer = tw.div`
  flex
`;

export const SelectionText = tw.span`
  ${({solutionStatus}) => solutionStatus ? "text-[#03B800]" :"text-[#DCDCDC]" }
`;