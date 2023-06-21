import tw from 'tailwind-styled-components';

export const CardWrapper = tw.div`
  w-[13rem]
  border
  border-solid
  border-[#DCDCDC]
  rounded-lg
  p-3
  cursor-pointer
  hover:border-ff7e22
  duration-300
  max-sm:w-[30rem]
`

export const CardTitle = tw.h3`
  mt-2
  text-sm
  font-bold
  text-[#101010]
`

export const CardContent = tw.p`
  mt-2
  text-xs
  text-[#747474]
`

export const SelectWrapper = tw.div`
  flex
  justify-end
  text-xs
  mt-2
`