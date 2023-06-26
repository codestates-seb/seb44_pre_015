import { configureStore } from '@reduxjs/toolkit';
import questionSlice from './questionSlice';
import mainSlice from './mainSlice';
import searchSlice from './searchSlice';

const store = configureStore({
  reducer: {
    question: questionSlice.reducer,
    main: mainSlice.reducer,
    search: searchSlice.reducer,
  },
});

export default store;
