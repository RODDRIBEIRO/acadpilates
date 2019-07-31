import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanoConta } from 'app/shared/model/plano-conta.model';

@Component({
  selector: 'jhi-plano-conta-detail',
  templateUrl: './plano-conta-detail.component.html'
})
export class PlanoContaDetailComponent implements OnInit {
  planoConta: IPlanoConta;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ planoConta }) => {
      this.planoConta = planoConta;
    });
  }

  previousState() {
    window.history.back();
  }
}
