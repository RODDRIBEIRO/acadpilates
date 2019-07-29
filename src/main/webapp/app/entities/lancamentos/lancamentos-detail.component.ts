import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILancamentos } from 'app/shared/model/lancamentos.model';

@Component({
  selector: 'jhi-lancamentos-detail',
  templateUrl: './lancamentos-detail.component.html'
})
export class LancamentosDetailComponent implements OnInit {
  lancamentos: ILancamentos;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ lancamentos }) => {
      this.lancamentos = lancamentos;
    });
  }

  previousState() {
    window.history.back();
  }
}
