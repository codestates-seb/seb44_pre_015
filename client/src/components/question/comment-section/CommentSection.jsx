import { useState } from 'react';
import { HeartUser, AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment } from "./CommentSection.styled";
import HeartBtn from "../../button/like/HeartBtn";
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import CommentBtn from '../../button/comment/CommentBtn'
import CommentEditBtn from '../../button/edit/CommentEditBtn';
import CommentRemoveBtn from '../../button/remove/CommentRemoveBtn'
import RecommentInput from './RecommentInput';


export default function CommentSection({ comment, answerId, memberId }) {
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

        <div className='pl-[20rem] gap-2 mb-3'>
          <CommentEditBtn />
          <CommentRemoveBtn />
        </div>

        { !isOpen && <CommentBtn setIsOpen={setIsOpen} />}
        { isOpen && <RecommentInput answerId={answerId} memberId={memberId}/>}
        </CommentBottomSectionWrap>
      </AllWrap>
    );
}