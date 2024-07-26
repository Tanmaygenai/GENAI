import React from 'react';
import MediaQuery from 'react-responsive';
const breakpoints = {
    desktop: '(min-width: 1025px)',
    tablet: '(min-width: 768px) and (max-width: 1024px)',
    phone: '(max-width: 767px)',
};
export default function Breakpoint(props) {
    //const breakpoint = breakpoints[props.name] || breakpoints.desktop;
    return (
        <MediaQuery {...props} deviceWidth={props.deviceWidth}>
            {props.children}
        </MediaQuery >
    );
}