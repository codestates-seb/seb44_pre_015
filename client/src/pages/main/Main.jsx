import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'
import InputSection from "../../components/question/input-section/InputSection"

export default function Main() {
  return (
    <MainContainer>
      <InputSection/>
      <SearchBox />
      <FilterBtn />
      
      
      <MainPostCard />
      <MainPostCard />
      <MainPostCard />
    </MainContainer>
  )
}
