import { Redevable } from "./redevable.model";
import { Taux } from "./taux.model";

export interface Taxe {
    id: number;
    label: string;
    montant: number;
    annee: number;
    redevable: Redevable;
   // terrain: Terrain;
   // categorie: Categorie;
    taux: Taux;
  }