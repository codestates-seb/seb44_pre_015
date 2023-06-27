import tw from "tailwind-styled-components";

export const UserInfoContainer = tw.section`
  flex
  gap-2
  ml-1
  items-center
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

export const Date = tw.span`
  text-[#797979]
  text-xs
`