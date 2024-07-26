import { createContext, useContext, useState } from "react";

const contextDefaultValues = {
    theme: {},
    setTheme: (data) => data,
    logoBase64: '',
    setLogoBase64: (data) => data
}

const MetaDataContext = createContext(contextDefaultValues)

export const MetaDataProvider = ({ children }) => {
    const [theme, setTheme] = useState(contextDefaultValues.theme)
    const [logoBase64, setLogoBase64] = useState(contextDefaultValues.logoBase64)

    return (
        <MetaDataContext.Provider
            value={{
                theme,
                setTheme,
                logoBase64,
                setLogoBase64
            }}
        >
            {children}
        </MetaDataContext.Provider>
    )
}

export default () => useContext(MetaDataContext)