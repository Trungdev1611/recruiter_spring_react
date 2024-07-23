import { Input } from 'antd';
import styled from 'styled-components';

// Định nghĩa styled component cho Input
const StyledInput = styled(Input.Password)`
  width: ${props => props.width || '100%'};
  border-color: ${props => props.borderColor || '#d9d9d9'};
  transition: border-color 0.1s ease; /* Hiệu ứng chuyển tiếp mượt mà cho border-color */
  &.ant-input-password {
    border-color: ${props => props.focusBorderColor || '#92e3b6'}  !important;
  }
  &:focus {
    border-color: ${props => props.focusBorderColor || '#92e3b6'}  !important;
    box-shadow: 0 0 0 2px ${props => props.focusBoxShadowColor || 'rgba(0, 0, 0, 0.1)'};
    
  }

`;

const InputPassWord = (props) => {
  const { placeholder ="", prefix, width, borderColor, focusBorderColor, focusBoxShadowColor, ...otherProps } = props;

  return (
    <StyledInput
      placeholder={placeholder }
      prefix={prefix}
      width={width}
      borderColor={borderColor}
      focusBorderColor={focusBorderColor}
      focusBoxShadowColor={focusBoxShadowColor}
      {...otherProps}
    />
  );
};

export default InputPassWord;