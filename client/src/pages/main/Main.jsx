import { useEffect, useState, useRef } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { setDatas } from '../../modules/mainSlice'
import { setSearch } from '../../modules/searchSlice';
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'


export default function Main() {
  const dispatch = useDispatch();
  const datas = useSelector(state => state.main.datas);
  const searchRef = useRef();
  const DEFAULT_PAGE = 6;

  const [ref, inView] = useInView();
  const [page, setPage] = useState(12);

  useEffect(()=> {
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=0&size=6`)
    .then(res => dispatch(setDatas(res.data)));
    searchRef.current.focus();
    dispatch(setSearch(''));
  }, [])

  useEffect(()=> {
    if(inView){
      axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=0&size=${page}`)
      .then(res => {
        dispatch(setDatas(res.data))
        if ( page < 100 ) setPage(prev => page + DEFAULT_PAGE);
      });
    }
  }, [inView])

    return(
      <MainContainer>
        <SearchBox searchRef={searchRef}/>
        <FilterBtn />
          {
            datas.map(data => <MainPostCard key={data.questionId} title={data.title} detail={data.detail} status={data.solutionStatus.toString()} viewCount={data.viewCount} votesCount={data.votesCount} answerCount={data.answerCount}/>)
          }
        <div ref={ref}/>
      </MainContainer>
    )
  
}
