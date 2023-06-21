import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag, tags, checkCount }) {
  const tagList =
    [
      {
        tagName: 'java',
        tagDescription: 'Java programming language'
      }, {
        tagName: 'spring-boot',
        tagDescription: 'Spring Boot framework'
      }, {
        tagName: 'python',
        tagDescription: 'Python programming language'
      },
      {
        tagName: 'JavaScript',
        tagDescription: 'JavaScript programming language'
      },
      {
        tagName: 'React',
        tagDescription: 'React programming language'
      },
      {
        tagName: 'etc',
        tagDescription: 'etc'
      }
    ]

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