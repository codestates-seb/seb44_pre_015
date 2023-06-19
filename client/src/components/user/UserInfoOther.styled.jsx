import tw from "tailwind-styled-components";

export const UserInfoContainer = tw.section`
  flex
  gap-3
  items-center
`

export const UserImg = tw.div`
  w-10
  h-10
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

export const UserInfoData = tw.div`
  flex
  gap-1.5
  relative
`

export const UserData = tw.span`
  text-sm
  base
  text-black
  font-semibold
  pr-2
  relative
`