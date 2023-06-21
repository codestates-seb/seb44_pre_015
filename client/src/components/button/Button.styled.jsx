
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

export const LogoutButton = tw(Button)`
  bg-[#FFFFFF]
  border-2
  text-black
  border-[#DCDCDC]
  hover:bg-[#DCDCDC]
`

export const FilterButton = tw.button`
  text-black
  bg-white
  rounded-full
  px-6
  py-1
  border-2
  border-[#DCDCDC]
  max-md:text-xs
  ${props => props.clicked ? `
    text-white
    border-[#FF7E22]
    bg-[#FFA564]
  ` : ''}
`;

