import tw from "tailwind-styled-components";

export const ButtonSm = tw.button`
  text-xs
  rounded-full
  px-3
  py-1
  transition duration-300 ease-in-out
  max-md:text-[0.65rem]
  max-md:px-2
  max-md:py-px
`;

export const CommentButton = tw(ButtonSm)`
  text-[#797979]
  bg-white
  border-solid
  border-2
  border-[#DCDCDC]
  hover:text-black
`