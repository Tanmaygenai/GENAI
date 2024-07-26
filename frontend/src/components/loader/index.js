import React from 'react'
import loaderGif from '../../assets/img/Loader.gif'

const Loader = () => {
  return (
    <div className='exavalu-loader'>
        <img src={loaderGif} alt='Loading' width={50} height={50} />
    </div>
  )
}

export default Loader