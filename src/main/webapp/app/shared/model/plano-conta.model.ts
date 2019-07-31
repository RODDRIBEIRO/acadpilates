export interface IPlanoConta {
  id?: number;
  descricao?: string;
  numero?: string;
}

export class PlanoConta implements IPlanoConta {
  constructor(public id?: number, public descricao?: string, public numero?: string) {}
}
