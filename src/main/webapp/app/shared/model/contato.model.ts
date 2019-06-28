export interface IContato {
  id?: number;
  tipo?: number;
  numero?: string;
  ddd?: string;
  email?: string;
  principal?: boolean;
  pessoaId?: number;
}

export class Contato implements IContato {
  constructor(
    public id?: number,
    public tipo?: number,
    public numero?: string,
    public ddd?: string,
    public email?: string,
    public principal?: boolean,
    public pessoaId?: number
  ) {
    this.principal = this.principal || false;
  }
}
