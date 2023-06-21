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

export default function QuestionSection({title, content, tags, createdAt}) {
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
          <UserInfoOwner createdAt={createdAt}/>
        </User>
      </div>

      <QuestionContainer>
        <Title>{title}</Title>
        <Tags tags={tags}/>
      </QuestionContainer>

      <Horizontal />
      
      <Content>{content}</Content>
    </EditContainer>
  );
}
