import React from 'react'
import { useNavigate } from 'react-router-dom' ;

const Landing = () => {

  const navigate = useNavigate();

  return (
    <>
    <section id='landing-container'>
      <div className='landing-img-cover'>
      </div>
      <div className='landing-title-container'>
        <div className='landing-title'>
          Read reviews. Write reviews.
        </div>
        <div className='landing-title'>
          Start discovering reviews with us.
        </div>
        <div className='landing-button' onClick={() => navigate("/review")}>
          Start reviewing now
        </div>
      </div>
    </section>
    <span className='recent-reviews'>
      <div className='rec-rev-title'>Recent reviews</div>
    </span>
    </>
  )
}

export default Landing