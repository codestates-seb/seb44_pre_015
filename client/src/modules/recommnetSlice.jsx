import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  answerId: '',
  memberId: '',
  commentDetail: '',
}

const recommentSlice = createSlice({
  name: "recommentSlice",
  initialState,
  reducers : {
    resetInput: (state) => {
      state.answerId = '';
      state.memberId = '';
      state.commentDetail = '';
    },
    typeAnswerId: (state, action) => {
      state.answerId = action.payload;
    },
    typeMemberId: (state, action) => {
      state.memberId = action.payload;
    },
    typeCommentDetail: (state, action) => {
      state.commentDetail = action.payload;
    }
  }
})

export const { resetInput, typeAnswerId, typeMemberId, typeCommentDetail } = recommentSlice.actions;
export default recommentSlice