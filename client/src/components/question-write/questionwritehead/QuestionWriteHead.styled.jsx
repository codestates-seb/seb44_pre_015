import tw from "tailwind-styled-components";

export const QuestionWrap = tw.div`
    flex
    items-center
    gap-1
    w-[40rem]
    max-sm:w-[29rem]
`;

export const QuestionHeadWrap = tw.div`
    flex
    flex-col
`;

export const QuestionHead = tw.p`
    text-xl  
    font-bold
`;


export const QuestionHeadtext = tw.p`
    text-xs
    text-slate-500
`;