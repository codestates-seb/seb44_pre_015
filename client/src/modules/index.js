import { configureStore }  from '@reduxjs/toolkit';
import questionSlice from './questionSlice'
import recommentSlice from './recommnetSlice';

const store = configureStore({
  reducer: {
    question: questionSlice.reducer,
    recomment: recommentSlice.reducer,
  }
});

export default store;