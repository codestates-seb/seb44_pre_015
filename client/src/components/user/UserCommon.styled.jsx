import tw from "tailwind-styled-components";

export const CircleImg = tw.div`
  rounded-full
  flex
  item-center
  overflow-hidden
`

export const Img = tw.img`
  w-full
  object-cover
  rounded-full
  object-cover
`

export const UserImgSx = tw(CircleImg)`
  w-8
  h-8
`

export const UserImgSm = tw(CircleImg)`
  w-10
  h-10
`

export const UserImgLg = tw(CircleImg)`
  w-48
  h-48
`