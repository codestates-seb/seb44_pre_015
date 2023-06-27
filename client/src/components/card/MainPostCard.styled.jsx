import tw from 'tailwind-styled-components'

export const MainPostCardContainer = tw.div`
  w-7/12
  border
  border-solid
  border-[#DCDCDC]
  rounded-lg
  p-3
  cursor-pointer
  hover:backdrop-brightness-90
  duration-300
  max-sm:w-[30rem]
`

export const CardTopSection = tw.div`
  flex
  justify-between
  items-center
  pr-2
`

export const CardMiddleSection = tw.div`
  mx-5
`

export const CardBottomSection = tw.div`
  mt-1
  flex
  justify-between
  pr-5
  items-center
  gap-x-2
`

export const TitleText = tw.h1`
  mt-1
  text-lg
  font-bold
`

export const ContentText = tw.p`
  mt-1
  text-xs
  font-light
  text-[#797979]
`

export const Counters = tw.div`
  flex
  ml-5
  gap-x-3.5
`

export const CountContainer = tw.div`
  flex
`

export const CountText = tw.span`
  text-[#CACACA]
  text-xs
`