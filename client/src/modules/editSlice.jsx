import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  title: "",
  detail: "",
  memberId: 1,
  tags: [],
  checkedCount: 0,
};

const editSlice = createSlice({
  name: "edit",
  initialState,
  reducers: {
    setQuestionData: (state, action) => {
      state = {...state, ...action.payload};
    },
    resetEditInput: (state) => {
      return initialState;
    },
    typeEditTitle: (state, action) => {
      state.title = action.payload;
    },
    typeEditDetail: (state, action) => {
      state.detail = action.payload;
    },
    typeEditTags: (state, action) => {
      state.tags = [...state.tags, { tagName: action.payload }];
    },
    updateEditTags: (state, action) => {
      state.tags = action.payload;
    },
  },
});

export const {
  setQuestionData,
  resetEditInput,
  typeEditTitle,
  typeEditDetail,
  typeEditTags,
  updateEditTags,
} = editSlice.actions;

export default editSlice.reducer;
