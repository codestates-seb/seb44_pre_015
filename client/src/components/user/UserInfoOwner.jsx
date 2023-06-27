import { useEffect, useState } from 'react';
import { UserImgSm, Img } from "./UserCommon.styled";
import { UserInfoContainer, UserInfoData, UserData, Date } from "./UserInfoOwner.styled"
import user from '../../assets/user.png'

export default function UserInfoOwner({ memberInfo, createdAt}) {
  const [info, setInfo] = useState('');
  
  useEffect(() => {
    if (memberInfo !== undefined) {
      setInfo(memberInfo);
    }
  }, [memberInfo]);
  
  return (
    <UserInfoContainer>
      <UserImgSm>
        <Img
          src={info.picture === undefined ? user : info.picture}
          alt="userImg" />
      </UserImgSm>
      <UserInfoData>
        <UserData>{info.userName}</UserData>
        <Date>{ createdAt && createdAt.slice(0,10) }</Date>
      </UserInfoData>
    </UserInfoContainer>
  );
}
