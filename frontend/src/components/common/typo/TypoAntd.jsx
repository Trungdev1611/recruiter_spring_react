import { Typography } from 'antd';
import "./Typo.css"
import styled from 'styled-components';

const StyledTitle = styled(Typography.Title)`
 color: ${props => props.color|| '#00b14f'} !important;
`;

const TypoAntd = ({ level = 2, children, ...otherProps }) => {
    return (
        <StyledTitle 
        level={level}
        {...otherProps}
        >{children}</StyledTitle>
    )
}

export const TextGreen = ({text, style}) => {
    return <span style={{color:"#30be70" ,...style }} >{text}</span>
}


export default TypoAntd