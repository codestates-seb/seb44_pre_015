import { MainContainer } from '../main/Main.styled';
import SearchBox from '../../components/search/SearchBox';
import FilterBtn from '../../components/filter/FilterBtn';
import MainPostCard from '../../components/card/MainPostCard';
import { useEffect, useState } from 'react';
import axios from 'axios';

export default function Main() {
  const [postCardData, setPostCardData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    getApi();
  }, []);

  const getApi = async () => {
    try {
      const response = await axios.get(
        'https://56d7-59-11-30-105.ngrok-free.app/questions/recent?page=0&size=100',
        {
          headers: {
            'Content-Type': 'application/json',
            'ngrok-skip-browser-warning': '69420',
          },
        }
      );
      setPostCardData(response.data);
      setFilteredData(response.data);
    } catch (error) {
      console.log(error);
    }
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

      {filteredData.map((data) => (
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
      ))}
    </MainContainer>
  );
}
