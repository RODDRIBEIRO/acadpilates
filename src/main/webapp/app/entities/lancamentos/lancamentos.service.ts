import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILancamentos } from 'app/shared/model/lancamentos.model';
import { Observable } from 'rxjs';

type EntityResponseType = HttpResponse<ILancamentos>;
type EntityArrayResponseType = HttpResponse<ILancamentos[]>;

@Injectable({ providedIn: 'root' })
export class LancamentosService {
  public resourceUrl = SERVER_API_URL + 'api/lancamentos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/lancamentos';

  constructor(protected http: HttpClient) {}

  create(lancamentos: ILancamentos): Observable<EntityResponseType> {
    return this.http.post<ILancamentos>(this.resourceUrl, lancamentos, { observe: 'response' });
  }

  update(lancamentos: ILancamentos): Observable<EntityResponseType> {
    return this.http.put<ILancamentos>(this.resourceUrl, lancamentos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILancamentos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILancamentos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<ILancamentos[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
