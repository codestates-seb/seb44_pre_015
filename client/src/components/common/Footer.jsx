import tw from "tailwind-styled-components";

const FooterContainer = tw.footer`
bg-[#797979]
text-white
text-xs
font-light
text-center
py-4
fixed
bottom-0
w-full
`;

function Footer() {
  return (
      
      <FooterContainer>
        <p>Copyright ⓒ 2023. MZ Developers all rights reserved.</p>
      </FooterContainer>
  );
}

export default Footer;
