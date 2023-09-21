import { type } from 'os'
import React from 'react'
import { useLocation } from 'react-router-dom'
type DashboardProps = {
    username: string,
    password: string,
}

function Dashboard() {
    const location = useLocation();
    const {state} = location;
    
  return (

    <div style={{marginTop:"5rem"}} className='place-items-center'>Hello {state.username}, Welcome to Ss website....</div>
  )
}

export default Dashboard