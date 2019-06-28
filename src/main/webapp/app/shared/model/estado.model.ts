import { IEndereco } from 'app/shared/model/endereco.model';

export interface IEstado {
  id?: number;
  nomeEstado?: string;
  paisId?: number;
  enderecos?: IEndereco[];
}

export class Estado implements IEstado {
  constructor(public id?: number, public nomeEstado?: string, public paisId?: number, public enderecos?: IEndereco[]) {}
}
