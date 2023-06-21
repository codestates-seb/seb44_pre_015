import HeartBtn from "../../button/like/HeartBtn";
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import CommentBtn from '../../button/comment/CommentBtn'

import { HeartUser,AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment, CommentAdd } from "./CommentSection.styled";

export default function CommentSection({ comment }) {
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
        <CommentBtn />
        </CommentBottomSectionWrap>
      </AllWrap>
    );
  }