import { configureStore }  from '@reduxjs/toolkit';
import questionSlice from './questionSlice'
import mainSlice from './mainSlice';

const store = configureStore({
  reducer: {
    question: questionSlice.reducer,
    main: mainSlice.reducer,
  }
});

export default store;