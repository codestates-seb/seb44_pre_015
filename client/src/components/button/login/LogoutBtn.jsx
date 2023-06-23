import { useNavigate } from 'react-router-dom';
import { LogoutButton } from '../Button.styled'

export default function LogoutBtn({ setIsLogin }) {
  const navigate = useNavigate();

  const onClickHandler = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('UID');
    localStorage.removeItem('isLogIn');
    localStorage.removeItem('userInfo');
    setIsLogin(false);
    return navigate('/');
  }

  return (
    <LogoutButton onClick={onClickHandler}>로그아웃</LogoutButton>
  )
}
