import { Moment } from 'moment';
import { IEndereco } from './endereco.model';

export interface IPessoa {
  id?: number;
  nome?: string;
  tipo?: number;
  cpfCnpj?: string;
  rgInscEst?: string;
  dataCadastro?: Moment;
  dataNascimento?: Moment;
  fotoContentType?: string;
  foto?: any;
  situacao?: boolean;
  enderecoLogradouro?: string;
  enderecos?: IEndereco[];
}

export class Pessoa implements IPessoa {
  constructor(
    public id?: number,
    public nome?: string,
    public tipo?: number,
    public cpfCnpj?: string,
    public rgInscEst?: string,
    public dataCadastro?: Moment,
    public dataNascimento?: Moment,
    public fotoContentType?: string,
    public foto?: any,
    public situacao?: boolean,
    public enderecoLogradouro?: string
  ) {
    this.situacao = this.situacao || false;
  }
}
