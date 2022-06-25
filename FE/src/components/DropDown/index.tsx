import { useState, useRef, useEffect } from 'react';
import Button from '@/components/Button';
import { Icon } from '@/components/Icon';
import Panel from '@/components/Dropdown/Panel';
import { IDropDown } from '@/components/Dropdown/type';
import { $DropDown } from '@/components/Dropdown/style';

export default function DropDown({ indicatorName, panelName, ...panelProps }: IDropDown) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState<string>('');
  const panelRef = useRef<HTMLDivElement>(null);

  const updateSelectedValue = (value: string) => {
    setSelectedValue(value);
  };

  const focusOnPanel = () => {
    if (isOpen) panelRef?.current?.focus();
  };

  useEffect(focusOnPanel, [isOpen]);

  return (
    <$DropDown>
      <Button styleType="mediumText" onClick={() => setIsOpen(!isOpen)}>
        {indicatorName}
        <Icon iconType={isOpen ? 'arrowUp' : 'arrowDown'} />
      </Button>
      {isOpen && (
        <Panel
          {...panelProps}
          panelRef={panelRef}
          title={panelName}
          selectedValue={selectedValue}
          updateSelectedValue={updateSelectedValue}
          hidePanel={() => setIsOpen(false)}
        />
      )}
    </$DropDown>
  );
}