import './Card.css'

const Card = ({children,CardClass,BodyClass}) => {
    return (
            <div className={CardClass}>
                            <div className={BodyClass}>
                            {children}
                            </div>
                        </div>
        
    )
}
export default Card