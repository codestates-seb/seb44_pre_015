import { useSearchParams, useNavigate } from 'react-router-dom';
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
  const [searchParams, setSearchParams]=useSearchParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const datas = useSelector(state => state.main.datas);
  const searchRef = useRef();
  const DEFAULT_PAGE = 6;

  const [ref, inView] = useInView();
  const [page, setPage] = useState(6);

  useEffect(()=> {

    if(searchParams.get('access_token') !== null){
      const accessToken = searchParams.get('access_token');
      const UID = Number(searchParams.get('UID'));
      localStorage.setItem('accessToken', JSON.stringify(accessToken));
      localStorage.setItem('UID', JSON.stringify(UID));
      localStorage.setItem('isLogIn', JSON.stringify(true));
      axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/members/${UID}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        }
      })
      .then(res => localStorage.setItem('userInfo', JSON.stringify(res.data)));
      return navigate('/');
    }

    axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/get/recent?page=0&size=6`)
    .then(res => dispatch(setDatas(res.data)));
    searchRef.current.focus();
    dispatch(setSearch(''));
  }, [])

  useEffect(()=> {
    if(inView){
      axios(`http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/get/recent?page=0&size=${page}`)
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
            datas.map(data => <MainPostCard key={data.questionId} title={data.title} detail={data.detail} status={data.solutionStatus.toString()} viewCount={data.viewCount} votesCount={data.votesCount} answerCount={data.answerCount} questionId={data.questionId} createdAt={data.createdAt} memberInfo={data.memberInfoDto} tags={data.tags}/>)
          }
        <div ref={ref}/>
      </MainContainer>
    )
  
}
