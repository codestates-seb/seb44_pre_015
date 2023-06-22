import { FilterButton } from '../button/Button.styled';
import { FilterContainer } from './FilterBtn.styled';


export default function FilterBtn({ filter, setFilter }) {
  const FILTER_LATEST = 'latest';
  const FILTER_LIKES = 'likes';
  const FILTER_SOLUTION = 'soltuion';

  const handleClick = (clickedFilter) => {
    setFilter(clickedFilter);
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