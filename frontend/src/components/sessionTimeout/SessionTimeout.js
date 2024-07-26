import React, { useEffect } from 'react';

function SessionTimeout({ onTimeout }) {
  useEffect(() => {
    let timeoutId = setTimeout(() => {
      onTimeout();
    },10 * 60 * 1000);

    function resetTimeout() {
      clearTimeout(timeoutId);
      timeoutId = setTimeout(() => {
        onTimeout();
      }, 10 * 60 * 1000);
    }

    document.addEventListener('mousemove', resetTimeout);
    document.addEventListener('keydown', resetTimeout);

    return () => {
      clearTimeout(timeoutId);
      document.removeEventListener('mousemove', resetTimeout);
      document.removeEventListener('keydown', resetTimeout);
    };
  }, [onTimeout]);

  return null;
}

export default SessionTimeout;
