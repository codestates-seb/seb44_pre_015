import { useEffect, useState } from 'react'
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import { MainContainer } from '../main/Main.styled'
import SearchBox from '../../components/search/SearchBox'
import FilterBtn from '../../components/filter/FilterBtn'
import MainPostCard from '../../components/card/MainPostCard'

const FILTER_LATEST = 'latest';
const FILTER_LIKES = 'likes';
const FILTER_SOLUTION = 'solution';

export default function Main() {
  const [ref, inView] = useInView();
  const [data, setData] = useState([]);
  const [filter, setFilter]= useState(FILTER_LATEST);
  const [page, setPage] = useState(12);

  useEffect(()=> {
    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=0&size=6`)
    .then(res => {
      console.log(res.data);
      setData(res.data);
    });
  }, [])

  useEffect(()=> {
    if(inView){
      axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=0&size=${page}`)
      .then(res => {
        setData(res.data);
        setPage(prev => prev + 6);
      });
    }
  }, [inView])

  useEffect(()=>{
    if(filter === FILTER_LATEST){
      let copy = [...data];
      copy.sort((a,b)=> new Date(b.createdAt) - new Date(a.createdAt))
      setData(copy);
    } else if( filter === FILTER_LIKES ){
      let copy = [...data];
      copy.sort((a,b)=> b.votesCount - a.votesCount);
      setData(copy);
    }
  }, [filter])

  switch(filter){
    case FILTER_SOLUTION:
      return(
        <MainContainer>
        <SearchBox />
        <FilterBtn filter={filter} setFilter={setFilter}/>
        {
          data.filter(el => el.solutionStatus).map(el =>
          <MainPostCard key={el.questionId} title={el.title} detail={el.detail} viewCount={el.viewCount} votesCount={el.votesCount} solutionStatus={el.solutionStatus} answerCount={el.answerCount
          }/>)
        }
        <div ref={ref}/>
      </MainContainer>
      )

    default :
    return(
      <MainContainer>
        <SearchBox />
        <FilterBtn filter={filter} setFilter={setFilter}/>
        {
          data.map(el =>
          <MainPostCard key={el.questionId} title={el.title} detail={el.detail} viewCount={el.viewCount} votesCount={el.votesCount} solutionStatus={el.solutionStatus} answerCount={el.answerCount
          }/>)
        }
        <div ref={ref}/>
      </MainContainer>
    )
  }
}
