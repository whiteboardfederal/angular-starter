import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ObjectService } from 'src/app/services/object-service.service';
import { GenericObject } from 'src/app/models/generic-object';

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss']
})
export class ItemFormComponent implements OnInit {

  item: GenericObject;

  constructor(private route: ActivatedRoute, private router: Router, private objectService: ObjectService) { 
    this.item = new GenericObject();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.objectService.save(this.item).subscribe(result => this.gotoItemList());
  }

  gotoItemList() {
    this.router.navigate(['/listobjects']);
  }

}
