import tw from "tailwind-styled-components";

export const Button = tw.button`
  text-sm
  text-white
  rounded-full
  px-6
  py-1
  transition duration-300 ease-in-out
  max-md:text-xs
`;

export const AnswerButton = tw(Button)`
  bg-[#FFA564]  
  border-solid
  border-2
  border-[#FF7E22]
  hover:bg-[#FF7E22]
`