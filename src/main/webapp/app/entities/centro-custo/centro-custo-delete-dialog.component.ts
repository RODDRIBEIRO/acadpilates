import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICentroCusto } from 'app/shared/model/centro-custo.model';
import { CentroCustoService } from './centro-custo.service';

@Component({
  selector: 'jhi-centro-custo-delete-dialog',
  templateUrl: './centro-custo-delete-dialog.component.html'
})
export class CentroCustoDeleteDialogComponent {
  centroCusto: ICentroCusto;

  constructor(
    protected centroCustoService: CentroCustoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.centroCustoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'centroCustoListModification',
        content: 'Deleted an centroCusto'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-centro-custo-delete-popup',
  template: ''
})
export class CentroCustoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ centroCusto }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CentroCustoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.centroCusto = centroCusto;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/centro-custo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/centro-custo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
