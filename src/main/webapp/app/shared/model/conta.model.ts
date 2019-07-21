export interface IConta {
  id?: number;
  descricao?: string;
  situacao?: boolean;
  tipo?: number;
  numeroConta?: string;
  agencia?: string;
  saldo?: number;
}

export class Conta implements IConta {
  constructor(
    public id?: number,
    public descricao?: string,
    public situacao?: boolean,
    public tipo?: number,
    public numeroConta?: string,
    public agencia?: string,
    public saldo?: number
  ) {}
}
