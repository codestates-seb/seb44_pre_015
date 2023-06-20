import tw from "tailwind-styled-components";

export const TagCheckContainer = tw.div`
  w-[40rem]
  max-sm:w-[29rem]
  flex
  flex-wrap
  gap-2
  max-md:gap-1
`

export const TagCheck = tw.input` 
  hidden
  [&:checked+label]:text-black
  [&:checked+label]:border-[#FFA564]
  [&:checked+label:after]:bg-[#FFA564]
`

export const TagLabel = tw.label`
  cursor-pointer
  w-[23.94%]
  max-sm:w-[30%]
  flex
  justify-between
  items-center
  border-solid
  border-[0.1rem]
  px-3
  py-3
  text-[#797979]
  text-sm
  transition duration-150 ease-in-out

  after:right-0 
  after:block 
  after:w-3
  after:h-3
  after:rounded-full
  after:bg-[#DCDCDC]  

  max-md:flex-auto
  max-md:w-full
`