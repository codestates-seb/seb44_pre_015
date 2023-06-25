import tw from 'tailwind-styled-components'

export const SelectionBtnContainer = tw.div`
  w-24
  flex
  justify-center
  items-center
  rounded-3xl
  border
  cursor-pointer
  ${({isSelected}) => isSelected ? 'border-[#03B800]' : 'border-[#CACACA]'}
`

export const SelectionBtnText = tw.span`
  ${({isSelected}) => isSelected ? 'text-[#03B800]' : 'text-[#DCDCDC]' }
  text-sm
`