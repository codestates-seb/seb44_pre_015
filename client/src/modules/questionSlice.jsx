import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  title: "",
  detail: "",
  memberId: 1,
  tags: [],
  checkedCount: 0,
}

const questionSlice = createSlice({
  name: "questionSlice",
  initialState,
  reducers: {
    resetInput: (state) => {
      state.title = '';
      state.detail = '';
      state.tags = [];
      state.checkedCount = 0;
    },
    typeTitle: (state, action) => {
      state.title = action.payload;
    },
    typeDetail: (state, action) => {
      state.detail = action.payload;
    },
    typeTags: (state, action) => {
      state.tags = [...state.tags, action.payload];
    },
    updateTags: (state, action) => {
      state.tags = action.payload
    },
    checkPlusTags: (state) => {
      state.checkedCount += 1
    },
    checkMinusTags: (state) => {
      state.checkedCount -= 1
    },
    
  }
})

export const {updateTags,checkPlusTags, checkMinusTags,resetInput, typeTitle, typeDetail, typeTags  } = questionSlice.actions;
export default questionSlice;