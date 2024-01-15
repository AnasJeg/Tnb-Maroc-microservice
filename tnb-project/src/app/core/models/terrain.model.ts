
export class Terrain{
  id!:number;
  nom!:string;
  surface!:number;
  categorie?: { id: number };
  redevable?: { id: number };
}

// import {Redevable} from "./redevable.model";
// import {Categorie} from "./categorie.model";
//
// export interface Terrain{
//   id:number;
//   nom:string;
//   surface:number;
//   categorie: Categorie;
//   redevable: Redevable;
// }



