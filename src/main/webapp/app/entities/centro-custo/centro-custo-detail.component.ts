import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentroCusto } from 'app/shared/model/centro-custo.model';

@Component({
  selector: 'jhi-centro-custo-detail',
  templateUrl: './centro-custo-detail.component.html'
})
export class CentroCustoDetailComponent implements OnInit {
  centroCusto: ICentroCusto;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ centroCusto }) => {
      this.centroCusto = centroCusto;
    });
  }

  previousState() {
    window.history.back();
  }
}
