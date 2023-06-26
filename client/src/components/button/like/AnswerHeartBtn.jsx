import { HeartButtonWrap } from "./HeartBtn.styled";
import { AiFillHeart } from 'react-icons/ai';

export default function AnswerHeartBtn() {

  return (
    <div>
      <HeartButtonWrap className='mt-3'>
        <AiFillHeart className={true ? "text-orange-500" : "text-gray-300"} style={{fontSize : '13px'}}/>
      </HeartButtonWrap>
      <p className="ml-2.5 text-xs text-[#797979]">00</p>
    </div>
  );
}
