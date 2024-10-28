// League interface
export interface League {
    id: number;
    name: string;
    teams: Team[]; // Array of Team entities associated with the league
}

// Team interface
export interface Team {
    teamid: number;
    teamName: string;
    standing: number;
    wins: number;
    draws: number;
    losses: number;
    teamRating: number;
    league?: League; // Optional League entity (to avoid circular dependency issues)
    players: Player[]; // Array of Player entities associated with the team
    manager?: Manager; // Optional Manager entity associated with the team
}

// Manager interface
export interface Manager {
    Id: number;
    firstName: string;
    lastName: string;
    nationality: string;
    managerRating: number;
    team?: Team; // Optional Team entity (to avoid circular dependency issues)
}

// Player interface
export interface Player {
    playerId: number;
    firstName: string;
    lastName: string;
    position: string;
    age: number;
    playerRating: number;
    team?: Team; // Optional Team entity (to avoid circular dependency issues)
}
