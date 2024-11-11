// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const TeamsTable = () => {
    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [currentTeam, setCurrentTeam] = useState({ teamName: '', standing: 0, wins: 0, draws: 0, losses:0, teamRating: 0 });
    const [isEditing, setIsEditing] = useState(false);

        // gets ID for a league from the backend. Need to get this ID for Create Delete and Update
        const extractIdFromLink = (team) => {
            const selfLink = team._links?.self?.href;
            if (selfLink) {
                const parts = selfLink.split('/');
                return parts[parts.length - 1]; 
            }
            return null;
        };

    //useEffect(() => {
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
        useEffect(() => {
            fetchTeams();
        }, []);
    //}, []);

    const handleSubmit = async (e) => {
        console.log("button clicked");
        e.preventDefault();
        setLoading(true);
        try {
            if (isEditing) {
                const teamId = extractIdFromLink(currentTeam);
                const response = await axios.put(
                    `http://localhost:8080/api/teams/${teamId}`, 
                    currentTeam,
                    
                );
                if (response.status === 200 || response.status === 204) {
                    await fetchTeams();
                    setIsDialogOpen(false);
                    setCurrentTeam({ teamName: '', standing: 0, wins: 0, draws: 0, losses:0, teamRating: 0  });
                    setIsEditing(false);
                }
            } else {
                const response = await axios.post(
                    'http://localhost:8080/api/teams', 
                    currentTeam,
                    {
                        headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                        }
                    }
                );
                if (response.status === 201 || response.status === 200) {
                    await fetchTeams();
                    setIsDialogOpen(false);
                    setCurrentTeam({ name: '' });
                }
            }
            setError(null);
        } catch (err) {
            console.log("error: ", err);
            setError(isEditing ? 'Error updating league' : 'Error creating league');
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (team) => {
        if (window.confirm('Are you sure you want to delete this team?')) {
            setLoading(true);
            try {
                const teamId = extractIdFromLink(team); // 'undefined ID' error deleting without this. 
                if (!teamId) {
                    throw new Error('Could not find team ID');
                }
                const response = await axios.delete(
                    `http://localhost:8080/api/teams/${teamId}`,
                );
                if (response.status === 200 || response.status === 204) {
                    await fetchTeams();
                    setError(null);
                }
            } catch (err) {
                console.log("error: ", err);
                if (err.response && err.response.status === 409) {
                    setError('This team cannot be deleted because it has relationships with other entities');
                } else {
                    setError('Error deleting team');
                }
            } finally {
                setLoading(false);
            }
        }
    };

    const openEditDialog = (team) => {
        setCurrentTeam({
            ...team,
            id: extractIdFromLink(team)  // ' undefined ID' error editing without this
        });
        setIsEditing(true);
        setIsDialogOpen(true);
    };

    const openCreateDialog = () => {
        setCurrentTeam({ teamName: '', standing: 0, wins: 0, draws: 0, losses:0, teamRating: 0 });
        setIsEditing(false);
        setIsDialogOpen(true);
    };

    if (loading) return <p>Loading teams...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="soccerTables">
            <h1>Teams</h1>
            <button className="action-button" onClick={openCreateDialog}>Add Team</button>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Standing</th>
                    <th>Wins</th>
                    <th>Draws</th>
                    <th>Losses</th>
                    <th>Team Rating</th>
                    <th>Actions</th>
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
                            <td>
                            <button className="action-button" onClick={() => openEditDialog(team)}>Edit</button>
                            <button className="action-button" onClick={() => handleDelete(team)}>Delete</button>
                            </td>
                        </tr>
                    </React.Fragment>
                ))}
                </tbody>
            </table>
            {isDialogOpen && (
                <div>
                    <h2>{isEditing ? 'Edit Team' : 'Create New Team'}</h2>
                    <form onSubmit={handleSubmit}>
                        <label>
                            Enter new Team name:&nbsp;
                            <input
                                value={currentTeam.teamName}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, teamName: e.target.value })}
                            />
                        </label>
                        <label>
                            Enter new Team standing:&nbsp;
                            <input
                                value={currentTeam.standing}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, standing: e.target.value })}
                            />
                        </label>
                        <label>
                            Enter new Team wins:&nbsp;
                            <input
                                value={currentTeam.wins}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, wins: e.target.value })}
                            />
                        </label>
                        <label>
                            Enter new Team draws:&nbsp;
                            <input
                                value={currentTeam.draws}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, draws: e.target.value })}
                            />
                        </label>
                        <label>
                            Enter new Team losses:&nbsp;
                            <input
                                value={currentTeam.losses}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, losses: e.target.value })}
                            />
                        </label>
                        <label>
                            Enter new Team rating:&nbsp;
                            <input
                                value={currentTeam.teamRating}
                                onChange={(e) => setCurrentTeam({ ...currentTeam, teamRating: e.target.value })}
                            />
                        </label>
                        <button className="action-button">{isEditing ? 'Update' : 'Create'}</button>
                        <button className="action-button" onClick={() => setIsDialogOpen(false)}>Cancel</button>
                    </form>
                </div>
            )}
        </div>
    );
};

export default TeamsTable;
