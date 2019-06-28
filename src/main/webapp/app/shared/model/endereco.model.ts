export interface IEndereco {
  id?: number;
  logradouro?: string;
  numero?: string;
  bairro?: string;
  complemento?: string;
  principal?: string;
  estadoId?: number;
  pessoaId?: number;
}

export class Endereco implements IEndereco {
  constructor(
    public id?: number,
    public logradouro?: string,
    public numero?: string,
    public bairro?: string,
    public complemento?: string,
    public principal?: string,
    public estadoId?: number,
    public pessoaId?: number
  ) {}
}
