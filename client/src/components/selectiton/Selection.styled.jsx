export const SelectionContainer = tw.div`
  flex
`;

export const SelectionText = tw.span`
  ${({isChecked}) => ( "text-[#03B800]" || "text-[#DCDCDC]") }
`;