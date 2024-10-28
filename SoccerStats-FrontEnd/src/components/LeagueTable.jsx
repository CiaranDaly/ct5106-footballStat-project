// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TeamTable from './TeamsTable';

const LeagueTable = () => {
    const [leagues, setLeagues] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedLeague, setSelectedLeague] = useState(null);

    useEffect(() => {
        const fetchLeagues = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/leagues');
                console.log("response.data._embedded.leagues: ", response.data._embedded.leagues);
                setLeagues(response.data._embedded?.leagues || []);
                setLoading(false);
            } catch (err) {
                setError('Error fetching leagues');
                setLoading(false);
            }
        };

        fetchLeagues();
    }, []);

    if (loading) return <p>Loading leagues...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <h1>Leagues</h1>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {leagues.map((league, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <td>{league.name}</td>
                            </tr>
                        </React.Fragment>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default LeagueTable;


