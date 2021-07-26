import { Component, OnInit } from '@angular/core';
import { GenericObject } from 'src/app/models/generic-object';
import { ObjectService } from 'src/app/services/object-service.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {

  items: GenericObject[] = [];

  constructor(private objectService: ObjectService) { }

  ngOnInit(): void {
    this.objectService.findAll().subscribe(data => {
      this.items = data;
    })
  }

}
