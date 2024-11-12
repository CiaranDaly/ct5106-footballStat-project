// src/components/LeagueTable.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ManagersTable = () => {
    const [managers, setManagers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedManager, setSelectedManager] = useState(null);
    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [currentManager, setCurrentManager] = useState({ name: '' });
    const [isEditing, setIsEditing] = useState(false);
    // gets ID for manager from the backend
    //this is used for the create update and delete.
    const extractIdFromLink = (managers) => {
        const selfLink = managers._links?.self?.href;
        if (selfLink) {
            const parts = selfLink.split('/');
            return parts[parts.length - 1]; //extracts last part of the url
        }
        return null;
    };
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
    useEffect(() => {

        fetchManagers();
    }, []);
    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (isEditing) {
                const managerssId = extractIdFromLink(currentManager);
                const response = await axios.put(
                    `http://localhost:8080/api/managers/${managersId}`,
                    currentManager,

                );
                if (response.status === 200 || response.status === 204) {
                    await fetchMangers();
                    setIsDialogOpen(false);
                    setCurrentManager({ name: '' });
                    setIsEditing(false);
                }
            } else {
                const response = await axios.post(
                    'http://localhost:8080/api/managers',
                    currentManager,
                    {
                        headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                        }
                    }
                );
                if (response.status === 201 || response.status === 200) {
                    await fetchManagers();
                    setIsDialogOpen(false);
                    setCurrentManager({ name: '' });
                }
            }
            setError(null);
        } catch (err) {
            setError(isEditing ? 'Error updating manager' : 'Error creating manager');
        } finally {
            setLoading(false);
        }
    };
    const handleDelete = async (manager) => {
        if (window.confirm('Are you sure you want to delete this manager?')) {
            setLoading(true);
            try {
                const managersId = extractIdFromLink(manager); // need id to delete the manager
                if (!managersId) {
                    throw new Error('Could not find manager ID');
                }
                const response = await axios.delete(
                    `http://localhost:8080/api/managers/${managersId}`,
                );
                if (response.status === 200 || response.status === 204) {
                    await fetchManagers();
                    setError(null);
                }

            } catch (err) {
                setError('Error deleting manager');
            } finally {
                setLoading(false);
            }
        }
    };
    const openEditDialog = (manager) => {
        setCurrentManager({
            ...manager,
            id: extractIdFromLink(manager)  // ' undefined ID' error editing without this
        });
        setIsEditing(true);
        setIsDialogOpen(true);
    };

    const openCreateDialog = () => {
        setCurrentManager({ name: '' });
        setIsEditing(false);
        setIsDialogOpen(true);
    };

    if (loading) return <p>Loading managers...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="soccerTables">
            <h1>Managers</h1>
            <button className="action-button" onClick={openCreateDialog}>Add Manager</button>
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
                            <td>
                                <button className="action-button" onClick={() => openEditDialog(manager)}>Edit</button>
                                <button className="action-button" onClick={() => handleDelete(manager)}>Delete</button>
                            </td>
                        </tr>
                    </React.Fragment>
                ))}
                </tbody>
            </table>
            {isDialogOpen && (
                <div>
                    <h2>{isEditing ? 'Edit Manager' : 'Create New Manager'}</h2>
                    <form onSubmit={handleSubmit}>
                        <label>
                            new Manager first name:&nbsp;
                            <input
                                value={currentManager.firstName}
                                onChange={(e) => setCurrentManager({...currentManager, firstName: e.target.value})}
                            />
                        </label>
                        <label>
                            new Manager last name:&nbsp;
                            <input
                                value={currentManager.lastName}
                                onChange={(e) => setCurrentManager({...currentManager, lastName: e.target.value})}
                            />
                        </label>
                        <label>
                            new Manager Nationality:&nbsp;
                            <input
                                value={currentManager.nationality}
                                onChange={(e) => setCurrentManager({...currentManager, nationality: e.target.value})}
                            />
                        </label>
                        <label>
                            new Manager rating:&nbsp;e
                            <input
                                value={currentManager.managerRating}
                                onChange={(e) => setCurrentManager({...currentManager, managerRating: e.target.value})}
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


export default ManagersTable;