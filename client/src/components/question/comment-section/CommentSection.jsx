import { useState } from 'react';
import { HeartUser, AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment } from "./CommentSection.styled";
import HeartBtn from "../../button/like/HeartBtn";
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import CommentBtn from '../../button/comment/CommentBtn'
import RecommentInput from './RecommentInput';


export default function CommentSection({ comment }) {
  const [isOpen, setIsOpen] = useState(false);

    return (
      <AllWrap>
        <CommentTopSectionWrap>
          <HeartUser>
          <HeartBtn />
          <UserInfoOwner />
          </HeartUser>
          <SelectionBtn />
        </CommentTopSectionWrap>

        <CommentBottomSectionWrap>
        <Comment>{ comment }</Comment>
        { !isOpen && <CommentBtn setIsOpen={setIsOpen} />}
        { isOpen && <RecommentInput />}
        </CommentBottomSectionWrap>
      </AllWrap>
    );
  }