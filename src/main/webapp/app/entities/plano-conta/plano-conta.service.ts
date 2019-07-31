import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanoConta } from 'app/shared/model/plano-conta.model';

type EntityResponseType = HttpResponse<IPlanoConta>;
type EntityArrayResponseType = HttpResponse<IPlanoConta[]>;

@Injectable({ providedIn: 'root' })
export class PlanoContaService {
  public resourceUrl = SERVER_API_URL + 'api/plano-contas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/plano-contas';

  constructor(protected http: HttpClient) {}

  create(planoConta: IPlanoConta): Observable<EntityResponseType> {
    return this.http.post<IPlanoConta>(this.resourceUrl, planoConta, { observe: 'response' });
  }

  update(planoConta: IPlanoConta): Observable<EntityResponseType> {
    return this.http.put<IPlanoConta>(this.resourceUrl, planoConta, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPlanoConta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPlanoConta[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<IPlanoConta[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
