import tw from "tailwind-styled-components";

export const UserInfoContainer = tw.section`
  flex
  gap-3
  items-center
`

export const UserImg = tw.div`
  w-12
  h-12
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
  gap-1
  relative
`

export const UserData = tw.span`
  text-sm
  base
  text-[#797979]
`

export const Line = tw.span`
  w-px
  bg-[#797979]
`