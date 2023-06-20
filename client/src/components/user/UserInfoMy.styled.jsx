import tw from "tailwind-styled-components";

export const UserInfoMyContainer = tw.section`
  flex
  gap-5
  items-center
`

export const UserInfoBox = tw.div`
  flex
  flex-col
  gap-3
`

export const InfoData = tw.div`
  flex
  flex-col
`

export const UserInfo = tw.strong`
 flex 
 items-center
 gap-1
`

export const SmTit = tw.span`
  text-sm
  text-[#797979]
`

export const NameEdit = tw.div`
  flex
  gap-1
`

export const NameInputWrapper = tw.div`
  inline-block
`

export const NameInput = tw.input`
  w-fit
  outline-none
  font-bold
  w-[fill-available]
`

export const NameEditBtn = tw.button`
  text-[#797979]
`