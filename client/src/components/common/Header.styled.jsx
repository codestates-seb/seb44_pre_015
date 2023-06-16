import tw from "tailwind-styled-components";

export const HeaderContainer = tw.header`
  w-full
  flex
  top-0
  sticky
  justify-between
  items-center
  drop-shadow-lg
  bg-white
  space-x-0
  py-5
  px-40
  z-50
  max-md:px-5
`;

export const LogoContainer = tw.h1`
  
`;

export const LogoImg = tw.img`
  w-52
  max-md:w-40
`;

export const Button = tw.button`
  text-lg
  text-white
  rounded-full
  px-6
  py-2
  transition duration-300 ease-in-out
  max-md:text-xs
`;

export const Nav = tw.nav`
  flex
  gap-2
`;

export const LoginBtn = tw(Button)`
  bg-[#FFA564]
  hover:bg-[#FF7E22] 
`;
