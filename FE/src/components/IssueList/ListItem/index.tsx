import Button from '@/components/common/Button';
import { Icon } from '@/components/common/Icon';
import { $ListItem, $Contents, $Title, $Info, $Text } from '@/components/IssueList/ListItem/style';
import { IListItem } from '@/components/IssueList/ListItem/type';
import Label from '@/components/common/Label';
import UserProfile from '@/components/common/UserProfile';

export default function ListItem({
  title,
  labelList,
  number,
  author,
  timestamp,
  milestone,
  status
}: IListItem) {
  return (
    <$ListItem>
      <$Contents>
        <$Title>
          <Icon iconType={status === 'OPEN' ? 'openLabel' : 'closeLabel'} />
          <$Text as="h3" size="large">
            {title}
          </$Text>
          {labelList &&
            labelList.map((value: string) => (
              <Label key={value} size="small" status="dark">
                {value}
              </Label>
            ))}
        </$Title>
        <$Info>
          <$Text size="small">{`#${number}`}</$Text>
          <$Text as="p" size="small">{`이 이슈가 ${author?.name}님에 의해 작성되었습니다.`}</$Text>
          <Button styleType="mediumText" gap="8px" fontWeight="normal">
            <Icon iconType="milestone" />
            {milestone}
          </Button>
        </$Info>
      </$Contents>
      <UserProfile src={author?.profile} size="small" />
    </$ListItem>
  );
}