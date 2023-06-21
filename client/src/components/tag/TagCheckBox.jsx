import { TagCheckContainer, TagCheck, TagCheckBoxed, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox({ handlerTag }) {
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
            onChange={() => handlerTag(el.tagName, el.tagDescription)}
            ></TagCheck>
            <TagLabel htmlFor={`${el.tagName}-${idx}`}><span>{el.tagName}</span></TagLabel>
          </TagCheckBoxed>

        ))
      }

      {/* <TagCheck type="checkbox" id="Javascript" value=""></TagCheck>
      <TagLabel htmlFor="Javascript"><span>Javascript</span></TagLabel>

      <TagCheck type="checkbox" id="React" value=""></TagCheck>
      <TagLabel htmlFor="React"><span>React</span></TagLabel>

      <TagCheck type="checkbox" id="Java" value=""></TagCheck>
      <TagLabel htmlFor="Java"><span>Java</span></TagLabel>

      <TagCheck type="checkbox" id="HTML" value=""></TagCheck>
      <TagLabel htmlFor="HTML"> <span>HTML</span></TagLabel>

      <TagCheck type="checkbox" id="C#" value=""></TagCheck>
      <TagLabel htmlFor="C#"><span>C#</span></TagLabel>

      <TagCheck type="checkbox" id="etc" value=""></TagCheck>
      <TagLabel htmlFor="etc"><span>etc</span></TagLabel> */}

    </TagCheckContainer>
  )
}