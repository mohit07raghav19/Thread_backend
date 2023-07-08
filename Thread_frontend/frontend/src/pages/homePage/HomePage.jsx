import React from 'react'
import HomepageSidebar from "./HomePageSidebar"
import { HomePagePosts } from './HomePagePosts'
import { HomePageConnections } from './HomePageConnections'
export const HomePage = () => {
  return (
<>     <HomepageSidebar />
     <HomePagePosts />
     <HomePageConnections />
     </>
  )
}
