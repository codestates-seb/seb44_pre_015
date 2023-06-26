import { useState } from 'react';
import { HeartUser, AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment } from "./CommentSection.styled";
import AnswerHeartBtn from '../../button/like/AnswerHeartBtn';
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import EditInput from '../input-section/EditInput';
import CommentBtn from '../../button/comment/CommentBtn'
import CommentEditBtn from '../../button/edit/CommentEditBtn';
import RecommentInput from './RecommentInput';


export default function CommentSection({ comment, answerId, memberId, memberInfo, createdAt, solutionStatus, writer, votesCount, answerVoteByMember }) {
  const [editIsOpen, setEditIsOpen]= useState(false);
  const [isOpen, setIsOpen] = useState(false);

    return (
      <AllWrap>
        <CommentTopSectionWrap>
          <HeartUser>
          <AnswerHeartBtn votesCount={votesCount} answerVoteByMember={answerVoteByMember} answerId={answerId}/>
          <UserInfoOwner memberInfo={memberInfo} createdAt={createdAt}/>
          </HeartUser>
          <div className='flex gap-3 mb-3 pt-3'>
            <CommentEditBtn memberId={memberId} memberInfo={memberInfo} setEditIsOpen={setEditIsOpen} editIsOpen={editIsOpen}/>
            <SelectionBtn solutionStatus={solutionStatus} answerId={answerId} writer={writer} />
          </div>
          
        </CommentTopSectionWrap>

        <CommentBottomSectionWrap>
        {!editIsOpen && <Comment>{ comment }</Comment>}
        {editIsOpen && <EditInput comment={comment} answerId={answerId} solutionStatus={solutionStatus}/>}

        { !isOpen && <CommentBtn setIsOpen={setIsOpen} isOpen={isOpen}/>}
        { isOpen && <RecommentInput answerId={answerId} memberId={memberId} />}
        </CommentBottomSectionWrap>
      </AllWrap>
    );
}