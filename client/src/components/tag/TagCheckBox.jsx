import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag, memtags, tags, checkCount, accessToken }) {
  const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

  const navigate = useNavigate();
  const [tagList, setTagList] = useState([])
  const selectMemTags = memtags?.filter((el) => el.select)

  const tagsData = () => {
    axios
      .get(`${PROXY}/questions/tags`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': `Bearer ${accessToken}`,
          },
        }
      )
      .then(response => {
        setTagList(response.data);
      })
      .catch(() => {
        navigate('/*');
      })
  }

  if (!memtags) {
    useEffect(() => {
      tagsData()
    })
  }


  return (
    <TagCheckContainer>
      {
        memtags
          ?
          memtags.map((el) => (
            <TagCheckBoxed key={el.tagName}>
              <TagCheck
                type="checkbox"
                id={el.tagName}
                value={el.tagName}
                checked={el.select}
                disabled={selectMemTags.length >= 3 && !el.select}
                onChange={() => handlerTag(el.tagName, el.select)}
              ></TagCheck>
              <TagLabel htmlFor={el.tagName}>
                <span>{el.tagName}</span>
              </TagLabel>
            </TagCheckBoxed>
          ))
          :
          tagList.map((el) => (
            <TagCheckBoxed key={el.tagName}>
              <TagCheck
                type="checkbox"
                id={el.tagName}
                value={el.tagName}
                checked={tags.some(item => item.tagName === el.tagName)}
                disabled={!tags.some(item => item.tagName === el.tagName) && checkCount >= 3}
                onChange={() => handlerTag(el.tagName)}
              ></TagCheck>
              <TagLabel htmlFor={el.tagName}>
                <span>{el.tagName}</span>
              </TagLabel>
            </TagCheckBoxed>
          ))
      }

    </TagCheckContainer>
  )
}