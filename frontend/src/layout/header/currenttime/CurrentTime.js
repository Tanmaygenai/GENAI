import React, { useEffect, useState } from "react";
const CurrentTime = () => {
    const [dateState,
        setDateState
    ] = useState(new Date())
    const refreshClock = () => {
        setDateState(new Date());
    }
    var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    useEffect(() => {
        const timerId = setInterval(refreshClock, 1000);
        return function cleanup() {
            clearInterval(timerId);
        };
    }, []);// eslint-disable-next-line react-hooks/exhaustive-deps


    return (
        <p className="date_dash">
            {dateState.getMinutes() < 10 ?
                days[dateState.getDay()] + ', ' + dateState.getDate() + ' ' + monthNames[dateState.getMonth()] + ' ' + dateState.getFullYear() + ',   ' + dateState.getHours() + ':' + 0 + dateState.getMinutes()+' hrs'
                :
                days[dateState.getDay()] + ', ' + dateState.getDate() + ' ' + monthNames[dateState.getMonth()] + ' ' + dateState.getFullYear() + ',   ' + dateState.getHours() + ':' + dateState.getMinutes()+ ' hrs'
            }
        </p>
    )

}
export default CurrentTime