import  { useContext } from 'react';
// Import shared profile context
import { ProfileContext } from '../App';

function Display() {
    // Access profile data from context
    const { profile } = useContext(ProfileContext);

    // Display user profile data (in a nice way)
    return (
        <div>
            <p> <span className = "profileDisplay">Name: </span> {profile.name}</p>

            <p> <span className = "profileDisplay">Email: </span> {profile.email}</p>

            <p> <span className = "profileDisplay"> Bio: </span> {profile.bio}</p>
        </div>
);
}

export default Display;