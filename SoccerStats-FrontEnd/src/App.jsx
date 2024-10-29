import { useState, createContext } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import Form from './components/Form'
import Display from "./components/Display.jsx";
import LeagueTable from './components/LeagueTable'
import TeamsTable from './components/TeamsTable'
import PlayersTable from './components/PlayersTable'
import ManagersTable from './components/ManagersTable'

// Creates shared context for the app allowing shared storage between components
export const ProfileContext = createContext();

function App() {
    // Keep track of user's information
    //const [profile, setProfile] = useState({name: "", email: "", bio: ""});

  return (
      // Shares profile data with Display and Form components
      //<ProfileContext.Provider value = {{profile, setProfile}}>
          <div>
              <LeagueTable /> <br/>
              <TeamsTable /> <br/>
            <PlayersTable /> <br/>
            <ManagersTable /> <br/>
          </div>
      //</ProfileContext.Provider>
  );
}

export default App;