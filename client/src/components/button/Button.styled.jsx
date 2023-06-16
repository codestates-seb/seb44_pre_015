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

export const OrangeBtn = tw(Button)`
  bg-[#FFA564]
  hover:bg-[#FF7E22] 
`;
