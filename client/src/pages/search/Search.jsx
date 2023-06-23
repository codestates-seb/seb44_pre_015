import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { filterData } from '../../modules/searchSlice';
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'


export default function Search() {
  const dispatch = useDispatch();
  const datas = useSelector(state => state.search.searchData);


  useEffect(()=>{
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/get/recent?page=0&size=100`)
    .then(res => dispatch(filterData(res.data)));
  }, [])

    return(
      <MainContainer>
        <SearchBox />
        <FilterBtn />
          {
            datas.map(data => <MainPostCard key={data.questionId} title={data.title} detail={data.detail} status={data.solutionStatus.toString()} viewCount={data.viewCount} votesCount={data.votesCount} answerCount={data.answerCount}/>)
          }
        <div />
      </MainContainer>
    )
  
}
