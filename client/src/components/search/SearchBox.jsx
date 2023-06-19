import { AiOutlineSearch } from "react-icons/ai";
import {MainSearchIconWrap, CenteredContainer, MainSearchContainer, MainSearchInput } from "./SearchBox.styled";

export default function SearchBox(){
    return (
    <CenteredContainer>
      <MainSearchContainer>
       <MainSearchIconWrap><AiOutlineSearch /></MainSearchIconWrap><MainSearchInput type="text" placeholder="질문을 검색해 보세요" />
      </MainSearchContainer>
    </CenteredContainer>
    );
  };
  