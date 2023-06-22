import { useDispatch, useSelector} from 'react-redux';
import { setSearch } from '../../modules/searchSlice'
import { useNavigate } from 'react-router-dom';
import { AiOutlineSearch } from "react-icons/ai";
import {MainSearchIconWrap, CenteredContainer, MainSearchContainer, MainSearchInput } from "./SearchBox.styled";

export default function SearchBox({ searchRef }){
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const searchWord = useSelector(state => state.search.searchWord);

  const onKeyHandler = (e) => {
    if(e.target.value === '' && e.key === 'Enter') return alert('공백은 입력할 수 없습니다.')
    if(e.key === 'Enter'){
      dispatch(setSearch(e.target.value));
      navigate('/search');
    }
  }

    return (
    <CenteredContainer>
      <MainSearchContainer>

       <MainSearchIconWrap>
        <AiOutlineSearch />
      </MainSearchIconWrap>
      
      <MainSearchInput
        type="text"
        placeholder="질문을 검색해 보세요"
        ref={searchRef}
        onKeyDown={(e)=> onKeyHandler(e)}
        />

      </MainSearchContainer>
    </CenteredContainer>
    );
  };
  