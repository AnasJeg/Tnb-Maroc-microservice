import { Redevable } from "./redevable.model";
import { Taux } from "./taux.model";
import { Terrain } from "./terrain.model";

export interface Taxe {
    id: number;
    label: string;
    montant: number;
    annee: number;
    redevable: Redevable;
   terrain: Terrain;
    taux: Taux;
  }