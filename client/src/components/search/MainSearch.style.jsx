import tw from "tailwind-styled-components";
export const CenteredContainer = tw.div`
     flex justify-center
`;

export const MainSearchContainer = tw.div`
    flex items-center justify-center
    w-[400px] 
    bg-white border border-gray-200
    rounded-full
    px-3 py-3
    
`;

export const MainSearchInput = tw.input`
    ml-2
    bg-transparent outline-none
    w-full
    rounded-2xl
    text-m
`;

export const MainSearchIconWrap = tw.div`
    text-3xl
`;
