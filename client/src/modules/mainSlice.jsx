import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  datas: [],
}

const mainSlice = createSlice({
  name: "mainSlice",
  initialState,
  reducers: {
    setDatas: (state, action) => {
      state.datas = action.payload;
      console.log(state.datas);
    },
    setLatest: (state) => {
      const sortedData = [...state.datas].sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
      state.datas = sortedData;
      console.log(state.datas);
    },
    setLikes: (state) => {
      const sortedData = [...state.datas].sort((a, b) => b.votesCount - a.votesCount);
      state.datas = sortedData;
      console.log(state.datas);
    },
    setSolution: (state) => {
      const sortedData = [...state.datas].sort((a, b) => b.solutionStatus - a.solutionStatus);
      state.datas = sortedData;
      console.log(state.datas);
    }        
  }
})

export const { setDatas, setLatest, setLikes, setSolution } = mainSlice.actions;
export default mainSlice;