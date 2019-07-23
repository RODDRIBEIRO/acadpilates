export interface ICentroCusto {
  id?: number;
  descricao?: string;
  situacao?: boolean;
}

export class CentroCusto implements ICentroCusto {
  constructor(public id?: number, public descricao?: string, public situacao?: boolean) {
    this.situacao = this.situacao || false;
  }
}
