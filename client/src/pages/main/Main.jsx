import { MainContainer } from '../main/Main.styled';
import SearchBox from '../../components/search/SearchBox';
import FilterBtn from '../../components/filter/FilterBtn';
import MainPostCard from '../../components/card/MainPostCard';
import { useEffect, useState } from 'react';
import { useInView } from 'react-intersection-observer';
import axios from 'axios';

export default function Main() {
  const [postCardData, setPostCardData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [page, setPage] = useState(0);
  const [ref, inView] = useInView();

  useEffect(() => {
    getApi();
  }, []);

  useEffect(() => {
    if (inView) {
      loadMore();
    }
  }, [inView]);

  const getApi = async () => {
    try {
      const response = await axios.get(
        `http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080/questions/recent?page=${page}&size=3`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            Accept: 'application/json',
          },
        }
      );
      setPostCardData((prevData) => [...prevData, ...response.data]);
      setFilteredData((prevData) => [...prevData, ...response.data]);
      setPage((prevPage) => prevPage + 1);
    } catch (error) {
      console.log(error);
    }
  };

  const loadMore = () => {
    getApi();
  };

  const handleFilter = (filter) => {
    let filteredResult = [];

    if (filter === 'latest') {
      filteredResult = postCardData.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    } else if (filter === 'like') {
      filteredResult = postCardData.sort((a, b) => b.votesCount - a.votesCount);
    } else if (filter === 'select') {
      filteredResult = postCardData.filter((data) => data.solutionStatus === true);
    }

    setFilteredData(filteredResult);
  };

  const handleSearch = (query) => {
    setSearchQuery(query);
    const filteredResult = postCardData.filter((data) =>
      data.title.toLowerCase().includes(query.toLowerCase())
    );
    setFilteredData(filteredResult);
  };

  return (
    <MainContainer>
      <SearchBox handleSearch={handleSearch} />
      <FilterBtn handleFilter={handleFilter} />

      {filteredData.map((data, index) => {
        if (index === filteredData.length - 1 && index % 3 === 2) {
          return (
            <div ref={ref} key={data.questionId}>
              <MainPostCard
                title={data.title}
                content={data.detail}
                votesCount={data.votesCount}
                viewCount={data.viewCount}
                answerCount={data.answerCount}
                createdAt={data.createdAt}
                memberId={data.memberId}
                tags={data.tags}
              />
            </div>
          );
        } else {
          return (
            <MainPostCard
              key={data.questionId}
              title={data.title}
              content={data.detail}
              votesCount={data.votesCount}
              viewCount={data.viewCount}
              answerCount={data.answerCount}
              createdAt={data.createdAt}
              memberId={data.memberId}
              tags={data.tags}
            />
          );
        }
      })}
    </MainContainer>
  );
}
