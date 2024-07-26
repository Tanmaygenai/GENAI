// import './Button.css'
const Button = ({ children, className, value, onSubmit, onClick, id }) => {
    return (
        <button className={className} value={value} onSubmit={onSubmit} onClick={onClick} id={id}>
            {children}
        </button>
    )
}
export default Button