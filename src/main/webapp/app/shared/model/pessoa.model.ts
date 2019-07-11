import { Moment } from 'moment';
import { IEndereco } from 'app/shared/model/endereco.model';
import { IContato } from 'app/shared/model/contato.model';

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
  categoria?: number;
  sexo?: number;
  enderecos?: IEndereco[];
  contatoes?: IContato[];
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
    public categoria?: number,
    public sexo?: number,
    public enderecos?: IEndereco[],
    public contatoes?: IContato[]
  ) {
    this.situacao = this.situacao || false;
  }
}
