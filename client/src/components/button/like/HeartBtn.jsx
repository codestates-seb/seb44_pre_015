import { useState } from "react";
import { HeartButtonWrap } from "./HeartBtn.styled";
import { AiFillHeart } from 'react-icons/ai';

export default function HeartBtn({ votesCount, questionId, memberId }) {
  const [isClicked, setIsClicked] = useState(false);
  const [clickCount, setClickCount] = useState(0);

  const handleClick = () => {
    if (isClicked) {
      setClickCount(clickCount - 1);
    } else {
      setClickCount(clickCount + 1);
    }
    setIsClicked(!isClicked);
  };

  return (
    <div>
      <HeartButtonWrap onClick={handleClick}>
        <AiFillHeart className={isClicked ? "text-orange-500" : "text-gray-300"} style={{fontSize : '13px'}}/>
      </HeartButtonWrap>
      <p className="ml-2.5 text-xs text-[#797979]">{ votesCount }</p>
    </div>
  );
}
