import React, { useState } from 'react';
import { MetaDataProvider } from './context/metaData';
import { LoaderProvider } from './context/loader';
import AppRoutes from './app-routes';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './assets/dart-scss/style.scss'
import './assets/dart-scss/jquery-ui.css'


function App() {
  const [deviceName, setDeviceName] = useState('desktop');
  const [deviceWidth, setDeviceWidth] = useState(window.innerWidth);
  React.useEffect(() => {
    // console.log("Window     " + window.location.reload)
    if (document.readyState === "complete") {
      window.addEventListener("resize", () => setTimeout(() => handleResize(), 100), false);
      // window.location.reload();
      return () => window.removeEventListener("resize", () => setTimeout(() => handleResize(), 100), false);
    } else {
      // debugger
      window.addEventListener('load', handleResize, false);
      return () => window.removeEventListener('load', handleResize, false);
    }
  }, []);
  const handleResize = () => {
    let width = window.innerWidth;
    setDeviceWidth(width);
    if (width > 1025) {
      setDeviceName("desktop");

    } else if (width > 767 && width < 1024) {

      setDeviceName("tablet");
    }
    else {
      setDeviceName("phone");
    }
  };

  return (
    // <Breakpoint name={deviceName} deviceWidth={deviceWidth}>
    <LoaderProvider>
      <MetaDataProvider>
        <AppRoutes />
      </MetaDataProvider>
    </LoaderProvider>
    // </Breakpoint>
  );
}
export default App;