// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ManagersTable = () => {
    const [managers, setManagers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedManager, setSelectedManager] = useState(null);

    useEffect(() => {
        const fetchManagers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/managers');
                console.log("response.data._embedded.managers: ", response.data._embedded.managers);
                setManagers(response.data._embedded?.managers || []); //either populkate the array or if data is missing set an empty array
                setLoading(false);
            } catch (err) {
                setError('Error fetching managers');
                setLoading(false);
            }
        };

        fetchManagers();
    }, []);

    if (loading) return <p>Loading managers...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="soccerTables">
            <h1>Managers</h1>
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Nationality</th>
                    <th>Manager Rating</th>
                </tr>
                </thead>
                <tbody>
                {managers.map((manager, index) => (
                    <React.Fragment key={index}>
                        <tr>
                            <td>{manager.firstName}</td>
                            <td>{manager.lastName}</td>
                            <td>{manager.nationality}</td>
                            <td>{manager.managerRating}</td>
                        </tr>
                    </React.Fragment>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ManagersTable;