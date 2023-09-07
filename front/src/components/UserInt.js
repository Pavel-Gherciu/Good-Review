import React, {useState} from 'react';
import user_icon from '../images/user_icon.png'
import settings from '../images/settings.png'
import notifs from '../images/notification.png'

export const UserInt = ({name,logout}) => {


  const [style, setStyle] = useState({display: 'none'});

  return (
    <div className='user-interface'>
      <div className='user-container'>
        <img src={user_icon} alt="" onMouseEnter={e => {setStyle({display: 'block'})}}/>
        <img src={settings} alt="" />
        <img src={notifs} alt="" /> 
      </div>
      <ul className='user-list' style={style} onMouseLeave={e => {setStyle({display: 'none'})}}>
        <li className='user-name'>
          {name}
        </li>
        <li>
          <div className='sign-out-button' onClick={logout}>
            SIGN OUT
          </div>
        </li>
      </ul>
    </div>
  )
}