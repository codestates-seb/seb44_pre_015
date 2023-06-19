import HeartBtn from "../../button/like/HeartBtn";
import UserInfoOwner from "../../user/UserInfoOwner";
import SelectionBtn from '../../selection/SelectionBtn'
import CommentBtn from '../../button/comment/CommentBtn'

import { HeartUser,AllWrap, CommentTopSectionWrap, CommentBottomSectionWrap, Comment, CommentAdd } from "./CommentSection.styled";

export default function CommentSection() {
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
        <Comment>
          I am using CameraX API and its ImageAnalysis to analyze each frame. Processing of frames takes some time and imageReader buffer gets filled resulting in this error - [ImageReader-...
        </Comment>
        <CommentBtn />
        </CommentBottomSectionWrap>
      </AllWrap>
    );
  }