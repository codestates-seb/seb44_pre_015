import tw from "tailwind-styled-components";

export const InputFormContainer = tw.section`
  w-[40rem]
  max-sm:w-[30rem]
  flex
  flex-col
  gap-3
`

export const EditInputFormContainer = tw.section`
  w-[35rem]
  max-sm:w-[30rem]
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
  mb-3
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
export const AnswerEditTextarea = tw.textarea`
  bg-white
  text-sm
  p-3
  outline-0
  h-[9rem]
`

export const AnswerBox = tw.div`
  flex
  justify-end
  border-t-[0.1rem]
  border-[#DCDCDC]
  border-solid
  p-3
`