import React, { useState } from "react";
import { HeartButtonWrap } from "./HeartBtn.style";
import { AiFillHeart } from 'react-icons/ai';

export default function HeartBtn() {
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
        <AiFillHeart className={isClicked ? "text-orange-500" : "text-gray-300"} />
      </HeartButtonWrap>
      <p className="ml-2.5">{clickCount}</p>
    </div>
  );
}
