import tw from "tailwind-styled-components";

export const Button = tw.button`
  text-sm
  text-white
  rounded-full
  px-6
  py-2
  transition duration-300 ease-in-out
  max-md:text-xs
`;

export const HeartButtonWrap = tw(Button)`
  bg-white 
  py-2 
  px-2
  rounded-full
  border border-gray-300
  
`;