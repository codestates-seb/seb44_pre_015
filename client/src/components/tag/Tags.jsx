import React from 'react';
import { TagContainer, Tag } from "./Tags.styled";

export default function Tags({ tags }) {
  return (
    <TagContainer>
      {tags &&
        tags.map((tag, index) => (
          <Tag key={index}>{tag.tagName}</Tag>
        ))}
    </TagContainer>
  );
}