import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag, memtags, tags, checkCount, accessToken }) {
  const navigate = useNavigate();
  const [tagList, setTagList] = useState([])
  const selectMemTags = memtags?.filter((el) => el.select)

  const tagsData = () => {
    axios
      .get('/questions/tags',
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

  useEffect(() => {
    if (!memtags) {
      tagsData()
    }
  }, [memtags]);

  const checkLimitExceeded = (el, tags) => {
    const isTagChecked = tags.some(item => item.tagName === el.tagName);
    if (!isTagChecked && checkCount >= 3) {
      alert('최대 3개의 태그만 선택할 수 있습니다.');
      return true;
    }
    return false;
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
                onChange={() => {
                  if (checkLimitExceeded(el, memtags)) return;
                  handlerTag(el.tagName, el.select);
                }}
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
                onChange={() => {
                  if (checkLimitExceeded(el, tags)) return;
                  handlerTag(el.tagName);
                }}
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
