// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const TeamsTable = () => {
    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedTeam, setSelectedTeam] = useState(null);

    useEffect(() => {
        const fetchTeams = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/teams');
                console.log("response.data._embedded.teams: ", response.data._embedded.teams);
                setTeams(response.data._embedded?.teams || []); //either populkate the array or if data is missing set an empty array
                setLoading(false);
            } catch (err) {
                setError('Error fetching teams');
                setLoading(false);
            }
        };

        fetchTeams();
    }, []);

    if (loading) return <p>Loading teams...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="soccerTables">
            <h1>Teams</h1>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Standing</th>
                    <th>Wins</th>
                    <th>Draws</th>
                    <th>Losses</th>
                    <th>Team Rating</th>
                </tr>
                </thead>
                <tbody>
                {teams.map((team, index) => (
                    <React.Fragment key={index}>
                        <tr>
                            <td>{team.teamName}</td>
                            <td>{team.standing}</td>
                            <td>{team.wins}</td>
                            <td>{team.draws}</td>
                            <td>{team.losses}</td>
                            <td>{team.teamRating}</td>
                        </tr>
                    </React.Fragment>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default TeamsTable;
