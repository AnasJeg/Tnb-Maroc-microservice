import { Role } from "./role.model";

export interface Redevable {
    id: number;
    nom: string;
    prenom: string;
    cin: string;
    password: string;
    role: Role;
  //  terrains?: Terrain[];
  }