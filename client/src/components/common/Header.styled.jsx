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
  py-3
  px-40
  z-50
  max-md:px-5
`;

export const LogoContainer = tw.h1`
  
`;

export const LogoImg = tw.img`
  w-48
  max-md:w-40
`;

export const Nav = tw.nav`
  flex
  gap-2
`;

export const UserButton = tw.button`
  bg-[#FFFFFF]
  border-2
  text-black
  border-[#DCDCDC]
  hover:bg-[#DCDCDC]
  text-sm
  rounded-full
  px-6
  py-2
  transition duration-300 ease-in-out
  max-md:text-xs
`