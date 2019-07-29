import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILancamentos } from 'app/shared/model/lancamentos.model';
import { LancamentosService } from './lancamentos.service';

@Component({
  selector: 'jhi-lancamentos-delete-dialog',
  templateUrl: './lancamentos-delete-dialog.component.html'
})
export class LancamentosDeleteDialogComponent {
  lancamentos: ILancamentos;

  constructor(
    protected lancamentosService: LancamentosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.lancamentosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'lancamentosListModification',
        content: 'Deleted an lancamentos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-lancamentos-delete-popup',
  template: ''
})
export class LancamentosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ lancamentos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LancamentosDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.lancamentos = lancamentos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/lancamentos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/lancamentos', { outlets: { popup: null } }]);
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
