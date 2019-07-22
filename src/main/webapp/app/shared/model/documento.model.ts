export interface IDocumento {
  id?: number;
  descricao?: string;
  situacao?: boolean;
}

export class Documento implements IDocumento {
  constructor(public id?: number, public descricao?: string, public situacao?: boolean) {
    this.situacao = this.situacao || false;
  }
}
