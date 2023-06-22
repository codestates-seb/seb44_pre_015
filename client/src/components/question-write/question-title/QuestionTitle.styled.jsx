import tw from 'tailwind-styled-components';

export const QWrapper = tw.div`
  flex
  flex-col
`;
export const QInputContainer = tw.div`
  w-1/2
  flex
  items-center
`;
export const QTitle = tw.h3`
  ml-2
  font-bold
  text-lg
`;
export const QInput = tw.input`
  w-[40rem]
  max-sm:w-[29rem]
  bg-white
  border-[#ccc]
  border-[1px]
  mt-3
  py-3
  px-4
  text-sm
`;
