import { IEstado } from 'app/shared/model/estado.model';

export interface IPais {
  id?: number;
  nomePais?: string;
  estados?: IEstado[];
}

export class Pais implements IPais {
  constructor(public id?: number, public nomePais?: string, public estados?: IEstado[]) {}
}
