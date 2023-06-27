import tw from "tailwind-styled-components";

export const GlobalStyle = tw.div`
    bg-gray-200
    h-screen
    flex
    justify-center
`;

export const Loginbox = tw.div`
    w-[33rem]
    max-sm:w-[28rem]
    bg-white
    drop-shadow-lg
    h-4/6
    flex
    justify-center
    items-center
    flex-col
    justify-around
    mt-20
    rounded-xl
    pt-10
`;

export const GoogleBtnWrap = tw.button`
    w-[25rem]
    max-sm:w-[23rem]
    bg-white
    h-10
    rounded-lg
    text-center
    text-sm
    text-slate-300
    border
    font-semibold
`;

export const GoogleBtn = tw.img`
    inline
    w-7
`;

export const NaverBtn = tw.img `
    inline
    w-5
`;

export const NaverBtnWrap = tw.button `
    w-[25rem]
    max-sm:w-[23rem]
    bg-green-500
    h-10
    rounded-lg
    text-center
    text-sm
    text-white
    mb-8
    border
    font-semibold
`;

export const HeadText = tw.p`
    text-2xl
    font-bold
    max-sm:text-xl
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

export const LogoImg = tw.img `
    w-44
`;