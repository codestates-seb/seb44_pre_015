import tw from "tailwind-styled-components";

export const AllWrap = tw.div`
  w-9/12
  flex
  flex-col
  justify-between
  mx-auto
`;

export const CommentTopSectionWrap = tw.div`
  flex
  justify-between
  items-center
  pr-2
  w-11/12
`;

export const SelectionWrap = tw.div`
  w-24
  h-8
  rounded-full
  bg-white
  flex
  items-center
  justify-center
  border
  border-gray-300
  text-lime-500
  font-bold
  text-sm
`;

export const CommentMiddleSectionWrap = tw.p`
  ml-48
  w-8/12
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
  flex-row
  justify-around
  w-96
  
`;