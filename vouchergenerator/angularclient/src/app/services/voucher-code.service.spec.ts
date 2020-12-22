import { TestBed } from '@angular/core/testing';

import { VoucherCodeService } from './voucher-code.service';

describe('VoucherCodeService', () => {
  let service: VoucherCodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoucherCodeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
