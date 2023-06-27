import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  searchWord: '',
  searchData: [],
};

const searchSlice = createSlice({
  name: 'searchSlice',
  initialState,
  reducers: {
    setSearch: (state, action) => {
      state.searchWord = action.payload;
    },
    filterData: (state, action)=> {
      let datas = [...action.payload];
      datas = datas.filter(data => data.title.includes(state.searchWord));
      state.searchData = datas;
    }
  }
})

export const { setSearch, filterData } = searchSlice.actions;
export default searchSlice