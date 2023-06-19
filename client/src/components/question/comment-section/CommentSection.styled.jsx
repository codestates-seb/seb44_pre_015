import tw from "tailwind-styled-components";

export const AllWrap = tw.div`
  w-[40rem]
  max-sm:w-[30rem]
  flex
  flex-col
  justify-between
`;

export const CommentTopSectionWrap = tw.div`
  flex
  justify-between
  items-center
`;

export const CommentBottomSectionWrap = tw.div`
  ml-16
`

export const Comment = tw.p`
  text-sm
  text-[#797979]
  leading-[1rem]
  my-3
`;

export const CommentAdd = tw.button`
  border
  border-gray-300
  text-xs
  w-16
  h-8
  rounded-full
  text-slate-300
  ml-48
  mt-3
`;

export const HeartUser = tw.div `
  flex
  gap-3
  items-center
`;