import { Categorie } from "./categorie.model";

export interface Taux {
    id: number;
    prix: number;
  categorie: Categorie;
  }