import { SelectionBtnContainer, SelectionBtnText } from './SelectionBtn.styled';
import { AiOutlineCheck } from "react-icons/ai";



export default function SelectionBtn({isChecked}) {
  return (
    <SelectionBtnContainer isChecked={isChecked}>
      <AiOutlineCheck color={ isChecked ? '#03B800' : '#DCDCDC'} />
      <SelectionBtnText isChecked={isChecked}>selection</SelectionBtnText>
    </SelectionBtnContainer>
  )
}
