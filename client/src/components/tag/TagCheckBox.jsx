import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag, tags}) {
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
        tagList.map((el, idx) => (
          <TagCheckBoxed key={idx}>
            <TagCheck 
            type="checkbox"
            id={`${el.tagName}-${idx}`}
            value={el.tagName}
            checked={tags.some(item => item.tagName === el.tagName)}
            onChange={() => handlerTag(el.tagName, el.tagDescription)}
            ></TagCheck>
            <TagLabel htmlFor={`${el.tagName}-${idx}`}><span>{el.tagName}</span></TagLabel>
          </TagCheckBoxed>
        ))
      }

    </TagCheckContainer>
  )
}