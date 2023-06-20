import tw from 'tailwind-styled-components';

export const ReviseContainer = tw.div`
  flex
  justify-center
  items-center
  inset-x-0
  mt-8
  mb-8
`

export const ReviseButton = tw.button`
  bg-[#FFA564]
  border-[#FF7E22]
  border-2
  hover:bg-[#FF7E22] 
  rounded-full
  text-sm
  text-white
  px-8
  py-2
  transition duration-300 ease-in-out
  max-md:text-xs
`