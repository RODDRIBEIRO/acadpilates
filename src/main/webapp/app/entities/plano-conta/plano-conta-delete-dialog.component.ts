import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanoConta } from 'app/shared/model/plano-conta.model';
import { PlanoContaService } from './plano-conta.service';

@Component({
  selector: 'jhi-plano-conta-delete-dialog',
  templateUrl: './plano-conta-delete-dialog.component.html'
})
export class PlanoContaDeleteDialogComponent {
  planoConta: IPlanoConta;

  constructor(
    protected planoContaService: PlanoContaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.planoContaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'planoContaListModification',
        content: 'Deleted an planoConta'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-plano-conta-delete-popup',
  template: ''
})
export class PlanoContaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ planoConta }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PlanoContaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.planoConta = planoConta;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/plano-conta', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/plano-conta', { outlets: { popup: null } }]);
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
