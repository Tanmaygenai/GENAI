import { createContext, useContext, useState } from "react";

const loaderDefaultValues = {
    loader: false,
    setLoader: (data) => data
}

const LoaderContext = createContext(loaderDefaultValues)

export const LoaderProvider = ({children}) => {
    const [loader, setLoader] = useState(loaderDefaultValues.loader)

    return (
        <LoaderContext.Provider
         value={{
            loader,
            setLoader
         }}
        >
            {children}
        </LoaderContext.Provider>
    )
}

export default () => useContext(LoaderContext)