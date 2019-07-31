import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILancamento } from 'app/shared/model/lancamento.model';
import { Observable } from 'rxjs';

type EntityResponseType = HttpResponse<ILancamento>;
type EntityArrayResponseType = HttpResponse<ILancamento[]>;

@Injectable({ providedIn: 'root' })
export class LancamentoService {
  public resourceUrl = SERVER_API_URL + 'api/lancamento';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/lancamento';

  constructor(protected http: HttpClient) {}

  create(lancamento: ILancamento): Observable<EntityResponseType> {
    return this.http.post<ILancamento>(this.resourceUrl, lancamento, { observe: 'response' });
  }

  update(lancamento: ILancamento): Observable<EntityResponseType> {
    return this.http.put<ILancamento>(this.resourceUrl, lancamento, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILancamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILancamento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  // Criado o serviço de busca do Backend
  search(req?: any): Observable<EntityArrayResponseType> {
    return this.http.get<ILancamento[]>(this.resourceSearchUrl, {
      params: createRequestOption(req),
      observe: 'response'
    });
  }
}
