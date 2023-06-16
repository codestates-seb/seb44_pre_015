import tw from "tailwind-styled-components";

export const GlobalStyle = tw.div`
    bg-gray-200
    h-[40.74rem]
    flex
    justify-center
`;

export const Loginbox = tw.div`
    bg-white
    drop-shadow-lg
    w-[32rem]
    h-[32rem]
    flex
    justify-center
    items-center
    flex-col
    justify-around
    mt-32
    rounded-xl
    pt-10
`;

export const GithubBtn = tw.button`
    bg-black
    w-96
    h-10
    rounded-xl
    text-center
    text-lg
    text-white
    mb-8
`;

export const HeadText = tw.p`
    text-2xl
    font-bold
`;

export const Logintext = tw.p`
    text-gray-400
    flex
    items-center
    text-sm
    mt-6
    
`;

export const Wrap = tw.div`
    flex
    flex-col
`;

export const IconWrap = tw.div`
    text-xl
    mr-4
`;
