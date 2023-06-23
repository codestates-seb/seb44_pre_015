import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  title: "",
  detail: "",
  memberId: 1,
  tags: [],
  checkedCount: 0,
}

const editSlice = createSlice({
  name: "edit",
  initialState,
  reducers: {
    setQuestionData: (state, action) => {
      const { title, detail, memberId, tags } = action.payload;
      state.title = title;
      state.detail = detail;
      state.memberId = memberId;
      state.tags = tags;
    },
    resetEditInput: (state) => {
      state.title = '';
      state.detail = '';
      state.tags = [];
      state.checkedCount = 0;
    },
    typeEditTitle: (state, action) => {
      state.title = action.payload;
    },
    typeEditDetail: (state, action) => {
      state.detail = action.payload;
    },
    typeEditTags: (state, action) => {
      state.tags.push(action.payload);
    },
    updateEditTags: (state, action) => {
      state.tags = action.payload;
    },
  }
})

export const { setQuestionData, resetEditInput, typeEditTitle, typeEditDetail, typeEditTags, updateEditTags } = editSlice.actions;
export default editSlice.reducer;
