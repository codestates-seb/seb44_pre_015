import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'

export default function Main() {
  return (
    <MainContainer>
      <SearchBox />
      <FilterBtn />
      
      
      
      <MainPostCard />
      <MainPostCard />
      <MainPostCard />
    </MainContainer>
  )
}
