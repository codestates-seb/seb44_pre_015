import tw from "tailwind-styled-components";

export const InputFormContainer = tw.section`
  w-full
  flex
  flex-col
  gap-3
`

export const AnswerForm = tw.form`
  flex
  flex-col
  border-[#DCDCDC]
  border-solid
  border-[0.1rem]
`

export const AnswerTextarea = tw.textarea`
  bg-white
  text-sm
  resize-none
  p-3
  w-full
  outline-0
  h-[12rem]
`

export const AnswerBox = tw.div`
  flex
  justify-end
  border-t-[0.1rem]
  border-[#DCDCDC]
  border-solid
  p-3
`

export const Button = tw.button`
  text-sm
  text-white
  rounded-full
  px-6
  py-2
  transition duration-300 ease-in-out
  max-md:text-xs
`;

export const AnswerButton = tw(Button)`
  bg-[#FFA564]  
  border-solid
  border-[0.1rem]
  border-[#FF7E22]
  hover:bg-[#FF7E22]
`