import tw from 'tailwind-styled-components'

export const InfoContainer = tw.div`
  flex
  flex-col
  items-center
  text-left
  mt-16
  mb-40
  justify-center
`

export const TitleContainer = tw.div`
  w-[40rem]
  max-sm:w-[30rem]
  pl-3
`

export const SubTitleContainer = tw.div`
  w-[40rem]
  max-sm:w-[30rem]
  pl-3
  mt-14
  mb-3
`

export const Line = tw.div`
  w-[40rem]
  border-[0.5px]
  border-[#DCDCDC]
  mt-10
`

export const QuestionCardContainer = tw.div`
  w-[41rem]
  max-sm:w-[30rem]
  flex-wrap
  flex
  gap-3
  justify-start
`