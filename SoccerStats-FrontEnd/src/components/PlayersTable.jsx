// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PlayersTable = () => {
    const [players, setPlayers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedPlayer, setSelectedPlayer] = useState(null);

    useEffect(() => {
        const fetchPlayers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/players');
                console.log("response.data._embedded.players: ", response.data._embedded.players);
                setPlayers(response.data._embedded?.players || []); //either populkate the array or if data is missing set an empty array
                setLoading(false);
            } catch (err) {
                setError('Error fetching players');
                setLoading(false);
            }
        };

        fetchPlayers();
    }, []);

    if (loading) return <p>Loading players...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="soccerTables">
            <h1>Players</h1>
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Position</th>
                    <th>Age</th>
                    <th>Player Rating</th>
                </tr>
                </thead>
                <tbody>
                {players.map((player, index) => (
                    <React.Fragment key={index}>
                        <tr>
                            <td>{player.firstName}</td>
                            <td>{player.lastName}</td>
                            <td>{player.position}</td>
                            <td>{player.age}</td>
                            <td>{player.playerRating}</td>
                        </tr>
                    </React.Fragment>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PlayersTable;