import UserInfoOwner from '../../user/UserInfoOwner';
import EditBtn from '../../button/edit/EditBtn';
import ReMoveBtn from '../../button/remove/ReMoveBtn';
import Tags from '../../tag/Tags';
import HeartBtn from '../../button/like/HeartBtn';
import {
  EditContainer,
  EditDiv,
  Heart,
  Horizontal,
  QuestionContainer,
  User,
  Title,
  Content
} from './QuestionSection.styled';

export default function QuestionSection() {
  return (
    <EditContainer>
      <EditDiv> 
        <EditBtn />
        <ReMoveBtn />
      </EditDiv>

      <div className="flex items-center">
        <Heart>
          <HeartBtn />
        </Heart>
        <User>
          <UserInfoOwner />
        </User>
      </div>

      <QuestionContainer>
        <Title>Clear Image Reader Buffer in CameraX API</Title>
        <Tags />
      </QuestionContainer>

      <Horizontal />
      
      <Content>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Nesciunt, dolore impedit sequi exercitationem molestias deserunt quia ab nostrum quaerat, ratione dolorem sunt placeat quo obcaecati. Laborum vel sunt quas nihil!
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Nesciunt, dolore impedit sequi exercitationem molestias deserunt quia ab nostrum quaerat, ratione dolorem sunt placeat quo obcaecati. Laborum vel sunt quas nihil!
      </Content>
    </EditContainer>
  );
}
