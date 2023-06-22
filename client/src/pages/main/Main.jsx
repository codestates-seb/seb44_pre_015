import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { setDatas } from '../../modules/mainSlice'
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'


export default function Main() {
  const dispatch = useDispatch();
  const datas = useSelector(state => state.main.datas);

  const [ref, inView] = useInView();
  const [page, setPage] = useState(12);

  useEffect(()=> {
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=0&size=100`)
    .then(res => dispatch(setDatas(res.data)));
  }, [])


    return(
      <MainContainer>
        <SearchBox />
        <FilterBtn />
          {
            datas.map(data => <MainPostCard key={data.questionId} title={data.title} detail={data.detail} status={data.solutionStatus.toString()} viewCount={data.viewCount} votesCount={data.votesCount} answerCount={data.answerCount}/>)
          }
        <div ref={ref}/>
      </MainContainer>
    )
  
}
