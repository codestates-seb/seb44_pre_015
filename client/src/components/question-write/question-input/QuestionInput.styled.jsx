import 'react-quill/dist/quill.snow.css';
import tw from 'tailwind-styled-components';
import ReactQuill from 'react-quill';


export const TitleWrap = tw.div`
  flex
  items-center
  gap-2
`;

export const TitleText = tw.h1`
  text-lg
  font-bold
`;

export const EditorContainer = tw.div`
  w-[40rem]
  max-sm:w-[29rem]
  h-60
`;

export const EditorContent = tw(ReactQuill)`
  h-full
`;
