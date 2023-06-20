import Selection from '../selection/Selection';
import Tags from '../tag/Tags';
import { CardContent, CardTitle, CardWrapper, SelectWrapper } from './QuestionCard.styled';

export default function QuestionCard() {
  return (
    <CardWrapper>
      
      <Tags />
      <CardTitle>이거 왜 안되는데 어떻게 작성해야하는지 알려주세욤</CardTitle>
      <CardContent>이거 왜 안되는지 모르겠어요...알려주세요.. 몰라요 저도..ㅠㅠ 알려줘..알려줘요ㅠㅠ </CardContent>

      <SelectWrapper>
        <Selection/>
      </SelectWrapper>
      
    </CardWrapper>
  )
}
