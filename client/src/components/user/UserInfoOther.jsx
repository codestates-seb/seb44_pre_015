import { useEffect, useState } from 'react';
import { UserImgSm, Img } from "./UserCommon.styled"
import user from '../../assets/user.png'
import { UserInfoContainer, UserInfoData, UserData } from "./UserInfoOther.styled"

export default function UserInfoOther() {
  const [userInfo, setUserInfo] = useState('');
  
  useEffect(()=> {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    if (userInfo !== null ) setUserInfo(userInfo);
  }, [])

  return (
    <UserInfoContainer>
      <UserImgSm>
        <Img
          src={userInfo === '' ? user : userInfo.picture}
          alt="userImg" />
      </UserImgSm>
      <UserInfoData>
        <UserData>{userInfo === '' ? 'please LogIn' : userInfo.userName }</UserData>
      </UserInfoData>
    </UserInfoContainer>
  )
}