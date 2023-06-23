import { useState } from 'react';
import { HeartUser, AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment } from "./CommentSection.styled";
import HeartBtn from "../../button/like/HeartBtn";
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import CommentBtn from '../../button/comment/CommentBtn'
import CommentEditBtn from '../../button/edit/CommentEditBtn';
import RecommentInput from './RecommentInput';


export default function CommentSection({ comment, answerId, memberId, memberInfo, createdAt }) {
  const [isOpen, setIsOpen] = useState(false);

    return (
      <AllWrap>
        <CommentTopSectionWrap>
          <HeartUser>
          <HeartBtn />
          <UserInfoOwner memberInfo={memberInfo} createdAt={createdAt}/>
          </HeartUser>
          <div className='flex gap-1 mb-3 pt-3'>
            <CommentEditBtn memberId={memberId}/>
            <SelectionBtn />
          </div>
          
        </CommentTopSectionWrap>

        <CommentBottomSectionWrap>
        <Comment>{ comment }</Comment>

        { !isOpen && <CommentBtn setIsOpen={setIsOpen} />}
        { isOpen && <RecommentInput answerId={answerId} memberId={memberId}/>}
        </CommentBottomSectionWrap>
      </AllWrap>
    );
}