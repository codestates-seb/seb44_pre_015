import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  datas: [],
  status: 'latest',
}

const mainSlice = createSlice({
  name: "mainSlice",
  initialState,
  reducers: {
    setDatas: (state, action) => {
      state.datas = action.payload;
    },
    setLatest: (state) => {
      const sortedData = [...state.datas].sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
      state.datas = sortedData;
    },
    setLikes: (state) => {
      const sortedData = [...state.datas].sort((a, b) => b.votesCount - a.votesCount);
      state.datas = sortedData;
    },
    setSolution: (state) => {
      const sortedData = [...state.datas].sort((a, b) => b.solutionStatus - a.solutionStatus);
      state.datas = sortedData;
    },
    setStatus: (state, action) => {
      state.status = action.payload;
    }
  }
})

export const { setDatas, setLatest, setLikes, setSolution, setStatus } = mainSlice.actions;
export default mainSlice;