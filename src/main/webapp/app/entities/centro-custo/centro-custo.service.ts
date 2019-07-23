import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICentroCusto } from 'app/shared/model/centro-custo.model';

type EntityResponseType = HttpResponse<ICentroCusto>;
type EntityArrayResponseType = HttpResponse<ICentroCusto[]>;

@Injectable({ providedIn: 'root' })
export class CentroCustoService {
  public resourceUrl = SERVER_API_URL + 'api/centro-custos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/centro-custos';

  constructor(protected http: HttpClient) {}

  create(centroCusto: ICentroCusto): Observable<EntityResponseType> {
    return this.http.post<ICentroCusto>(this.resourceUrl, centroCusto, { observe: 'response' });
  }

  update(centroCusto: ICentroCusto): Observable<EntityResponseType> {
    return this.http.put<ICentroCusto>(this.resourceUrl, centroCusto, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICentroCusto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICentroCusto[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<ICentroCusto[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
