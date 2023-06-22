import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { setLatest, setLikes, setSolution } from '../../modules/mainSlice';
import { FilterButton } from '../button/Button.styled';
import { FilterContainer } from './FilterBtn.styled';

const FILTER_LATEST = 'latest';
const FILTER_LIKES = 'likes';
const FILTER_SOLUTION = 'soltuion';

export default function FilterBtn() {
  const dispatch = useDispatch();
  const [filter, setFilter] = useState(FILTER_LATEST);

  const handleClick = (clickedFilter) => {
    if( clickedFilter === FILTER_LATEST ){
      setFilter(FILTER_LATEST);
      dispatch(setLatest());
    } else if (clickedFilter === FILTER_LIKES){
      setFilter(FILTER_LIKES);
      dispatch(setLikes());
    } else {
      setFilter(FILTER_SOLUTION);
      dispatch(setSolution());
    }
  };

  return (
    <FilterContainer>
      <FilterButton clicked={filter === FILTER_LATEST ? 'true' : undefined} onClick={() => handleClick(FILTER_LATEST)}>
        최신순
      </FilterButton>
      <FilterButton clicked={filter === FILTER_LIKES ? 'true' : undefined} onClick={() => handleClick(FILTER_LIKES)}>
        좋아요순
      </FilterButton>
      <FilterButton clicked={filter === FILTER_SOLUTION ? 'true' : undefined} onClick={() => handleClick(FILTER_SOLUTION)}>
        채택순
      </FilterButton>
    </FilterContainer>
  )
}