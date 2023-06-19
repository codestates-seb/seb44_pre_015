import HeartBtn from "../../like/HeartBtn";
import UserInfoOwner from "../../../user/UserInfoOwner";

import { HeartUser,AllWrap,CommentTopSectionWrap, SelectionWrap, CommentMiddleSectionWrap, CommentAdd } from "./CommentSection.styled";

export default function CommentSection() {
    return (
      <AllWrap>
        <CommentTopSectionWrap>
          <HeartUser>
          <HeartBtn />
          <UserInfoOwner />
          </HeartUser>
          <SelectionWrap>✓Selection</SelectionWrap>
        </CommentTopSectionWrap>
        <CommentMiddleSectionWrap>
          I am using CameraX API and its ImageAnalysis to analyze each frame. Processing of frames takes some time and imageReader buffer gets filled resulting in this error - [ImageReader-...
        </CommentMiddleSectionWrap>
        <CommentAdd>댓글쓰기</CommentAdd>
      </AllWrap>
    );
  }