import { TagCheckContainer, TagCheck, TagLabel } from "./TagCheckBox.styled"

export default function TagCheckBox() {
  return (
    <TagCheckContainer>

      <TagCheck type="checkbox" id="Javascript" value=""></TagCheck>
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
      <TagLabel htmlFor="etc"><span>etc</span></TagLabel>

    </TagCheckContainer>
  )
}