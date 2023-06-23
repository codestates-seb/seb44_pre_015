import { useState } from 'react';
import { FilterButton } from '../button/Button.styled';
import { FilterContainer } from './FilterBtn.styled';

const FILTER_LATEST = 'latest';
const FILTER_LIKES = 'likes';
const FILTER_SOLUTION = 'soltuion';

export default function MyFilterBtn() {
  const [filter, setFilter] = useState(FILTER_LATEST);

  const handleClick = (clickedFilter) => {
    if( clickedFilter === FILTER_LATEST ){
      setFilter(FILTER_LATEST);
    } else if (clickedFilter === FILTER_LIKES){
      setFilter(FILTER_LIKES);
    } else {
      setFilter(FILTER_SOLUTION);
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