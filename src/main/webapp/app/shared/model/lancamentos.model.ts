import { Moment } from 'moment';

export interface ILancamentos {
  id?: number;
  dataCompetencia?: Moment;
  dataConciliacao?: Moment;
  valor?: number;
  tipo?: number;
  historico?: string;
  numeroDocumento?: string;
  pessoaId?: number;
  pessoaNome?: string;
  contaId?: number;
  contaDescricao?: string;
  documentoId?: number;
  documentoDescricao?: string;
  centroCustoId?: number;
  centroCustoDescricao?: string;
}

export class Lancamentos implements ILancamentos {
  constructor(
    public id?: number,
    public dataCompetencia?: Moment,
    public dataConciliacao?: Moment,
    public valor?: number,
    public status?: number,
    public tipo?: number,
    public historico?: string,
    public numeroDocumento?: string,
    public pessoaId?: number,
    public pessoaNome?: string,
    public contaId?: number,
    public contaDescricao?: string,
    public documentoId?: number,
    public documentoDescricao?: string,
    public centroCustoId?: number,
    public centroCustoDescricao?: string
  ) {}
}
