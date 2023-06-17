import { useState } from 'react';
import { FilterButton } from '../button/Button.styled';
import { FilterContainer } from './FilterBtn.styled';


export default function FilterBtn() {
  const [filter, setFilter] = useState("latest");

  const handleClick = (clickedFilter) => {
    setFilter(clickedFilter);
  };


  return (
    <FilterContainer>
      <FilterButton clicked={filter === 'latest'} onClick={() => handleClick('latest')}>
        최신순
      </FilterButton>
      <FilterButton clicked={filter === 'like'} onClick={() => handleClick('like')}>
        좋아요순
      </FilterButton>
      <FilterButton clicked={filter === 'select'} onClick={() => handleClick('select')}>
        채택순
      </FilterButton>
    </FilterContainer>
  )
}

