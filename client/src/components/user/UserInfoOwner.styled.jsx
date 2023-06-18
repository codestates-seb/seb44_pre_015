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
  gap-1.5
  relative
`
export const UserData = tw.span`
  text-sm
  base
  text-[#797979]
  pr-2
  relative
  
  after:absolute
  after:top-2/4
  after:right-0
  after:translate-y-[-50%]
  after:translate-x-[-50%]
  after:block
  after:w-px
  after:h-3
  after:bg-[#797979]

  last:pr-0
  last:after:hidden
`