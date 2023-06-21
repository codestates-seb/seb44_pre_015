import { useState } from 'react';
import { FilterButton } from '../button/Button.styled';
import { FilterContainer } from './FilterBtn.styled';

export default function FilterBtn({ handleFilter }) {
  const [filter, setFilter] = useState('latest');

  const handleClick = (clickedFilter) => {
    setFilter(clickedFilter);
    handleFilter(clickedFilter);
  };

  return (
    <FilterContainer>
      <FilterButton clicked={filter === 'latest' ? 'true' : undefined} onClick={() => handleClick('latest')}>
        최신순
      </FilterButton>
      <FilterButton clicked={filter === 'like' ? 'true' : undefined} onClick={() => handleClick('like')}>
        좋아요순
      </FilterButton>
      <FilterButton clicked={filter === 'select' ? 'true' : undefined} onClick={() => handleClick('select')}>
        채택순
      </FilterButton>
    </FilterContainer>
  );
}
