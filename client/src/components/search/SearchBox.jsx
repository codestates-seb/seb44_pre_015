import { AiOutlineSearch } from "react-icons/ai";
import { MainSearchIconWrap, CenteredContainer, MainSearchContainer, MainSearchInput } from "./SearchBox.styled";
import { useState } from 'react';

export default function SearchBox({ handleSearch }) {
  const [searchInput, setSearchInput] = useState('');

  const handleChange = (event) => {
    setSearchInput(event.target.value);
    handleSearch(event.target.value);
  };

  return (
    <CenteredContainer>
      <MainSearchContainer>
        <MainSearchIconWrap><AiOutlineSearch /></MainSearchIconWrap>
        <MainSearchInput type="text" placeholder="질문을 검색해 보세요" value={searchInput} onChange={handleChange} />
      </MainSearchContainer>
    </CenteredContainer>
  );
}
