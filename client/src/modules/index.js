import { configureStore }  from '@reduxjs/toolkit';
import questionSlice from './questionSlice'

const store = configureStore({
  reducer: {
    question: questionSlice.reducer,
  }
});

export default store;