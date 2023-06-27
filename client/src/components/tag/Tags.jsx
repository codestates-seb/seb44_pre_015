import { useEffect, useState } from 'react'
import { TagContainer, Tag } from "./Tags.styled"

export default function Tags({ tags }){

  return(
    <TagContainer>
      {
        tags && tags.map((tag, idx) => <Tag key={idx}>{tag.tagName}</Tag>)
      }
    </TagContainer>    
  )
}