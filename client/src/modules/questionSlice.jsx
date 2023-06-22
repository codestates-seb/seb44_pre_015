import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  title : "",
  detail : "",
  memberId : 1,
  tags : [],
}

const questionSlice = createSlice({
  name: "questionSlice",
  initialState,
  reducers: {
    resetInput: (state) => {
      state.title = '';
      state.detail = '';
      state.tags = [];
    },
    typeTitle: (state, action) => {
      state.title = action.payload;
    },
    typeDetail: (state, action) => {
      state.detail = action.payload;
    },
    typeTags: (state, action) => {
      state.tags = [...state.tags, action.payload];
    }
  }
})

export const { resetInput, typeTitle, typeDetail, typeTags } = questionSlice.actions;
export default questionSlice;