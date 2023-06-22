import tw from "tailwind-styled-components";

export const SelectionContainer = tw.div`
  flex
`;

export const SelectionText = tw.span`
  text-[#DCDCDC]
  ${({status}) => status==='true' && "text-[#03B800]" }
`;