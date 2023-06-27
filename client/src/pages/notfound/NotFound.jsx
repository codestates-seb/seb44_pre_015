import { Link } from 'react-router-dom';
import ReturnBtn from '../../components/button/return/ReturnBtn';
import { BtnContainer, Number, TextContainer, TextOne, TextTwo } from './NotFound.styled';

export default function NotFound() {
  return (
    <>
      <TextContainer>
        <Number>404</Number>
        <TextOne>OOPS! NOTHING WAS FOUND</TextOne>
        <TextTwo>페이지를 찾을 수 없습니다! :(</TextTwo>

        <BtnContainer>
          <Link to="/">
            <ReturnBtn />
          </Link>
        </BtnContainer>
      </TextContainer>
    </>
  );
}
