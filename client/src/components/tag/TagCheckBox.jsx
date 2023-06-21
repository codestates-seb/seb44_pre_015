import { tagList } from "./tagList"
import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag, tags, checkCount }) {
  return (
    <TagCheckContainer>
      {
        tagList.map((el) => (
          <TagCheckBoxed key={el.tagName}>
            <TagCheck
              type="checkbox"
              id={el.tagName}
              value={el.tagName}
              checked={tags.some(item => item.tagName === el.tagName)}
              disabled={!tags.some(item => item.tagName === el.tagName) && checkCount >= 3}
              onChange={() => handlerTag(el.tagName, el.tagDescription)}
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